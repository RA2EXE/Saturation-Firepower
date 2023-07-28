exports.modName = "饱和火力"

exports.mod = Vars.mods.locateMod(exports.modName);

exports.addToResearch = (content, research) => {
	if (!content) {
		throw new Error('content is null!');
	}
	if (!research.parent) {
		throw new Error('research.parent is empty!');
	}
	var researchName = research.parent;
	var customRequirements = research.requirements;
	var objectives = research.objectives;

	var lastNode = TechTree.all.find(boolf(t => t.content == content));
	if (lastNode != null) {
		lastNode.remove();
	}

	var node = new TechTree.TechNode(null, content, customRequirements !== undefined ? customRequirements : content.researchRequirements());
	var currentMod = exports.mod;
	if (objectives) {
		node.objectives.addAll(objectives);
	}

	if (node.parent != null) {
		node.parent.children.remove(node);
	}

	// find parent node.
	var parent = TechTree.all.find(boolf(t => t.content.name.equals(researchName) || t.content.name.equals(currentMod.name + "-" + researchName)));

	if (parent == null) {
		throw new Error("Content '" + researchName + "' isn't in the tech tree, but '" + content.name + "' requires it to be researched.");
	}

	// add this node to the parent
	if (!parent.children.contains(node)) {
		parent.children.add(node);
	}
	// reparent the node
	node.parent = parent;
};