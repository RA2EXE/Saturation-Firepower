package SFire.ai;

import arc.math.*;
import arc.util.*;
import mindustry.ai.types.FlyingAI;
import mindustry.content.UnitTypes;
import mindustry.entities.*;
import mindustry.entities.units.*;
import mindustry.gen.*;
import mindustry.world.*;

import static mindustry.Vars.state;
//完全用不了啊nnd
//aiController = GunShipAI::new;

public class GunShipAI extends FlyingAI {
    public float orbitRadius = 30 * 8;
    //度/秒
    public float orbitSpeed = 360 / 5f;
    //攻击范围
    public float attackRange = 30;
    public @Nullable Unit target;

    public GunShipAI() {
        super();
    }

    public GunShipAI(float orbitRadius, float orbitSpeed, float attackRange) {
        super();
    }

    //选取目标-靠近到range-获取目标地XY-三角函数计算对应转圈速度的移动位置-开火
    @Override
    public void updateMovement() {
        if (target != null && unit.hasWeapons()) {
            moveTo(target, unit.type.range * 0.8f);
            unit.lookAt(target);
        }

        float distance = unit.dst(target);
        if (distance > attackRange) {
            moveTo(target, attackRange - 50f);
        } else {
            //炮击位置
            float targetX = target.x;
            float targetY = target.y;
            //转圈
            float angle = (Time.time * orbitSpeed) % 360f;
            float rad = Mathf.degreesToRadians * angle;
            float offsetX = orbitRadius * Mathf.cos(rad);
            float offsetY = orbitRadius * Mathf.sin(rad);
            float newX = targetX + offsetX;
            float newY = targetY + offsetY;
            unit.move(newX, newY);
        }
    }
}
