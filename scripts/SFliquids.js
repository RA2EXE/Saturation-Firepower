function newLiquid(name) {
	exports[name] = (() => {
		let myLiquid = extend(Liquid, name, {});
		return myLiquid;
	})();
}
newLiquid("纳米流体")
newLiquid("硝化重油")
//newLiquid("活化纳米流体")