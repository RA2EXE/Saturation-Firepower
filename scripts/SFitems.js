function newItem(name) {
	exports[name] = (() => {
		let myItem = extend(Item, name, {});
		return myItem;
	})();
}
newItem("水桶")
newItem("矿渣桶")
newItem("石油桶")
newItem("冷冻液桶")
newItem("纳米流体桶")
newItem("泰勒合金")
newItem("莱普合金")
newItem("镄")
newItem("铬")
newItem("一级协议")
newItem("二级协议")
newItem("三级协议")
newItem("硅钢")
newItem("裂位能")
newItem("集束")
newItem("纳米核")