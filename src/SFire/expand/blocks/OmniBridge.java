package SFire.expand.blocks;

import arc.func.Boolf;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
import arc.math.Angles;
import arc.math.Mathf;
import arc.math.geom.Point2;
import arc.util.Eachable;
import arc.util.Time;
import arc.util.Tmp;
import mindustry.core.Renderer;
import mindustry.entities.units.BuildPlan;
import mindustry.gen.Building;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.graphics.Lod;
import mindustry.graphics.Pal;
import mindustry.world.Edges;
import mindustry.world.Tile;
import mindustry.world.blocks.distribution.ItemBridge;

import static mindustry.Vars.*;

public class OmniBridge extends ItemBridge {

    public OmniBridge(String name) {
        super(name);
        squareSprite = false;
        allowDiagonal = true;
        canOverdrive = false;
    }

    public void setStats() {
        super.setStats();
    }


    private static int currentFindX, currentFindY;
    private static BuildPlan currentPlan;
    private static final Boolf<BuildPlan> planFinder = other -> other.block == currentPlan.block && currentPlan != other && currentFindX == other.x && currentFindY == other.y;

    @Override
    public void drawPlanConfigTop(BuildPlan plan, Eachable<BuildPlan> list) {
        if (plan.config instanceof Point2 p && (Math.abs(p.x) <= range && Math.abs(p.y) <= range && (p.x == 0 || p.y == 0))) {
            currentFindX = plan.x + p.x;
            currentFindY = plan.y + p.y;
            currentPlan = plan;
            var otherReq = findPlan(list, currentFindX, currentFindY, planFinder);

            if (otherReq != null) {
                drawBridge(plan, otherReq.drawx(), otherReq.drawy(), 0);
            }
        }
    }

    @Override
    public void drawBridge(BuildPlan req, float ox, float oy, float flip) {
        if (Mathf.zero(Renderer.bridgeOpacity)) return;
        Draw.alpha(Renderer.bridgeOpacity);

        Lines.stroke(bridgeWidth);

        Tmp.v1.set(ox, oy).sub(req.drawx(), req.drawy()).setLength(tilesize / 2f);

        Lines.line(
                bridgeRegion,
                req.drawx() + Tmp.v1.x,
                req.drawy() + Tmp.v1.y,
                ox - Tmp.v1.x,
                oy - Tmp.v1.y, false
        );

        Draw.rect(arrowRegion, (req.drawx() + ox) / 2f, (req.drawy() + oy) / 2f,
                Angles.angle(req.drawx(), req.drawy(), ox, oy) + flip);

        Draw.reset();
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid) {
        //super.drawPlace(x, y, rotation, valid);
        //Tile link = findLink(x, y);

        Lines.stroke(1f);
        Draw.color(Pal.placing);
        Drawf.dashCircle(x * tilesize + offset, y * tilesize + offset, range * tilesize, Pal.placing);

        Draw.reset();
    }

    @Override
    public boolean positionsValid(int x1, int y1, int x2, int y2, int baseRange) {
        int dx = x1 - x2, dy = y1 - y2;
        if (dx * dx + dy * dy <= baseRange * baseRange) {
            return true;
        } else {
            return false;
        }
    }

    public class OmniBridgeBuild extends ItemBridgeBuild {
        @Override
        public void drawSelect() {//选择高亮
            super.drawSelect();

            Lines.stroke(1f);

            Drawf.dashCircle(x, y, range * tilesize, Pal.accent);
            Draw.reset();
        }

        @Override
        public void drawConfigure() {
            Drawf.select(x, y, tile.block().size * tilesize / 2f + 2f, Pal.accent);

            for (int dx = -range; dx <= range; dx++) {
                for (int dy = -range; dy <= range; dy++) {
                    if (dx == 0 && dy == 0) continue;
                    if (!positionsValid(tile.x, tile.y, tile.x + dx, tile.y + dy)) continue;

                    Tile other = world.tile(tile.x + dx, tile.y + dy);
                    if (other == null) continue;
                    if (!linkValid(tile, other)) continue;

                    boolean linked = other.pos() == link;
                    Drawf.select(
                            other.drawx(), other.drawy(),
                            other.block().size * tilesize / 2f + 2f + (linked ? 0f : Mathf.absin(Time.time, 4f, 1f)),
                            linked ? Pal.place : Pal.breakInvalid
                    );
                }
            }
        }


        @Override
        public void draw() {
            //super.draw();

            Draw.rect(block.region, x, y);
            Draw.z(Layer.power);

            Tile other = world.tile(link);
            if (!linkValid(tile, other)) return;
            if (Mathf.zero(Renderer.bridgeOpacity)) return;

            float angle = tile.angleTo(other);
            float dist = Mathf.dst(tile.x, tile.y, other.x, other.y) - 1f;//角度？

            if (pulse) {
                Draw.color(Color.white, Color.black, Mathf.absin(Time.time, 6f, 0.07f));
            }//这一坨好像是运货动态箭头
            float warmup = hasPower ? this.warmup : 1f;
            Draw.alpha((fadeIn ? Math.max(warmup, 0.25f) : 1f) * Renderer.bridgeOpacity);

            //头尾
            Draw.rect(endRegion, x, y, angle + 90);//出发点
            Draw.rect(endRegion, other.worldx(), other.worldy(), angle + 270);

            //桥身
            Lines.stroke(bridgeWidth);
            Tmp.v1.set(x, y).sub(other.worldx(), other.worldy()).setLength(tilesize / 2f).scl(-1f);
            Lines.line(bridgeRegion,
                    x + Tmp.v1.x,
                    y + Tmp.v1.y,
                    other.worldx() - Tmp.v1.x,
                    other.worldy() - Tmp.v1.y, false);

            //箭头
            Draw.color();
            if (Lod.l1) {
                int arrows = (int) (dist * tilesize / arrowSpacing);
                float dx = Mathf.cosDeg(angle), dy = Mathf.sinDeg(angle);
//捏吗的原来给d4i删了就能万向
                for (int i = 0; i < arrows; i++) {
                    Draw.alpha(Mathf.absin(i - time / arrowTimeScl, arrowPeriod, 1f) * warmup * Renderer.bridgeOpacity * Lod.alpha1);
                    Draw.rect(arrowRegion,
                            x + dx * (tilesize / 2f + i * arrowSpacing + arrowOffset),
                            y + dy * (tilesize / 2f + i * arrowSpacing + arrowOffset),
                            angle);
                }
            }
            Draw.reset();
        }

        @Override
        protected boolean checkAccept(Building source, Tile link) {
            if (tile == null || linked(source)) return true;

            if (linkValid(tile, link)) {
                float linkAng = Angles.angle(tile.drawx(), tile.drawy(), link.drawx(), link.drawy());
                var edge = Edges.getFacingEdge(source, this);
                float edgeAng = edge == null ? Float.NaN
                        : Angles.angle(tile.drawx(), tile.drawy(), edge.drawx(), edge.drawy());
                if (Float.isNaN(edgeAng)) return true;

                return Math.abs(Angles.angleDist(linkAng, edgeAng)) > 5;//攻角限位器
            }
            return false;

            /*if(linkValid(tile, link)){
                int rel = relativeTo(link);获取连接/不需要
                var facing = Edges.getFacingEdge(source, this);查面向->角度
                int rel2 = facing == null ? -1 : relativeTo(facing);二号连接？=-1否则=面向，换角度

                //this is a bug, but it is kept for compatibility, see: https://github.com/Anuken/Mindustry/issues/9257#issuecomment-1801998747
                /*
                for(int j = 0; j < incoming.size; j++){
                    int v = incoming.items[j];
                    if(relativeTo(Point2.x(v), Point2.y(v)) == rel2){
                        return false;
                    }
                }
                return rel != rel2;
            }*/
        }


    }
}
