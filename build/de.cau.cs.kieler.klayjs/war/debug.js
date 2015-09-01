genSvg = function(graph) {

	var svg = '<svg xmlns:xlink="http://www.w3.org/1999/xlink" xmlns="http://www.w3.org/2000/svg" w="'
			+ graph.width + '" h="' + graph.height + '">\n';

	if (graph.children) {
		for (var i = 0; i < graph.children.length; i++) {
			var node = graph.children[i];
			node.parent = graph;
			svg += genSvgNode(node, {
				x : 0,
				y : 0
			});
		}
	}

	if (graph.edges) {
		for (var i = 0; i < graph.edges.length; i++) {
			var edge = graph.edges[i];
			svg += genSvgEdge(edge, {
				x : 0,
				y : 0
			});
		}
	}

	svg += '</svg>';
	return svg;
}

genSvgNode = function(node, offset) {

	var svg = '<rect x="'
			+ (node.x + offset.x  )
			+ '" y="'
			+ (node.y + offset.y  )
			+ '" width="'
			+ node.width
			+ '" height="'
			+ node.height
			+ '" style="stroke-width: 1px; stroke: black; fill: grey; stroke-opacity: 0.1; fill-opacity: 0.1;"></rect>\n';
	
	var padding = node.padding || {};
	var childOffset = {
		x : node.x + offset.x + (padding.left || 0),
		y : node.y + offset.y + (padding.top || 0)
	};

	if (node.children) {
		for (var i = 0; i < node.children.length; i++) {
			var child = node.children[i];
			child.parent = node;
			svg += genSvgNode(child, childOffset);
		}
	}

	if (node.ports) {
		for (var i = 0; i < node.ports.length; i++) {
			var port = node.ports[i];
			port.parent = node;
			svg += genSvgPort(port, childOffset);
		}
	}

	if (node.edges) {
		for (var i = 0; i < node.edges.length; i++) {
			var edge = node.edges[i];
			edge.parent = node;
			svg += genSvgEdge(edge, childOffset);
		}
	}

	return svg;
}

genSvgPort = function(port, offset) {
	
	return '<rect x="'
			+ (port.x + offset.x )
			+ '" y="'
			+ (port.y + offset.y)
			+ '" width="'
			+ port.width
			+ '" height="'
			+ port.height
			+ '" style="stroke-width: 1px; stroke: black; fill: red; stroke-opacity: 0.1; fill-opacity: 0.1;"></rect>\n';
}

genSvgEdge = function(edge, offset) {

	var svg = '<path d="M ' + (edge.sourcePoint.x + offset.x) + ' '
			+ (edge.sourcePoint.y + offset.y) + ' L ';

	if (edge.bendPoints) {
		for (var i = 0; i < edge.bendPoints.length; i++) {
			var bp = edge.bendPoints[i];
			svg += (bp.x + offset.x) + ' ' + (bp.y + offset.y) + ' L ';
		}
	}

	svg += (edge.targetPoint.x + offset.x) + ' '
			+ (edge.targetPoint.y + offset.y);
	svg += '" style="fill: none; stroke: red; stroke-width: 1px;" />\n';

	return svg;
}