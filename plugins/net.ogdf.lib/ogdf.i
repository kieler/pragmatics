%module Ogdf
%{

#include <ogdf/basic/geometry.h>
#include <ogdf/basic/Graph_d.h>
#include <ogdf/basic/GraphAttributes.h>
#include <ogdf/basic/UMLGraph.h>
#include <ogdf/labeling/ELabelInterface.h>
#include <ogdf/labeling/ELabelPosSimple.h>
#include <ogdf/module/HierarchyLayoutModule.h>
#include <ogdf/layered/FastHierarchyLayout.h>
#include <ogdf/module/LayoutModule.h>
#include <ogdf/module/UMLLayoutModule.h>
#include <ogdf/layered/SugiyamaLayout.h>
#include <ogdf/misclayout/BalloonLayout.h>
#include <ogdf/planarity/PlanarizationLayout.h>

using namespace ogdf;

%}

%include "enums.swg"
%javaconst(1);

%pragma(java) jniclasscode=%{
    static {
        try {
            System.loadLibrary("ogdf");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Failed to load native 'ogdf' library. \n" + e);
        }
    }
%}

/*
 * File: ogdf/basic/geometry.h
 */
enum Orientation {
	topToBottom,
	bottomToTop,
	leftToRight,
	rightToLeft
};

/*
 * File: ogdf/basic/geometry.h
 */
class DPoint {
public:
	DPoint();
	DPoint(double, double);
};
%extend DPoint {
	double getX() const {
		return $self->m_x;
	}
	double getY() const {
		return $self->m_y;
	}
};

/*
 * File: ogdf/basic/geometry.h
 */
class DRect {
public:
	DRect();
	DRect(const DPoint &, const DPoint &);
	DRect(double x1, double y1, double x2, double y2);
	
	double width() const;
	double height() const;
	
	const DPoint& p1() const;
	const DPoint& p2() const;
	
	bool contains(const DPoint &) const;
};

// make the list iterator foreach conform
%typemap(javainterfaces) ListConstIterator<DPoint> %{ Iterator<DPoint> %}
%typemap(javaimports) ListConstIterator<DPoint> %{ import java.util.Iterator; %}

template<class E>
class ListConstIterator {
public:
	ListConstIterator();
};
// make the list iterator foreach conform
%extend ListConstIterator {
	bool hasNext() const {
		return $self->valid();
	}
	const E next() {
		E element = *(*$self);
		$self->operator++();
		return element;
	}
	void remove() {}
};

%template(DPointListConstIterator) ListConstIterator<DPoint>;
%template(EdgeElementListConstIterator) ListConstIterator<EdgeElement*>;

// make the list foreach conform
%typemap(javainterfaces) List<DPoint> %{ Iterable<DPoint> %}

template<class E>
class List {
public:
	List();
	
	bool empty() const;
	int size() const;
};
// make the list foreach conform
%extend List {
	const ListConstIterator<E> iterator() const {
		return $self->begin();
	}
};

%template(DPointList) List<DPoint>;
%template(EdgeElementList) List<EdgeElement*>;

class DPolyline : public List<DPoint> {
	DPolyline();
};

class NodeElement {
	NodeElement(int id);
};

class EdgeElement {
	EdgeElement(NodeElement*, NodeElement*, int);
};

/*
 * File: ogdf/basic/Graph_d.h
 */
class Graph {
public:
	// this is likely to change in future ogdf versions
	enum EdgeType {
		association = 0,
		generalization = 1,
		dependency = 2
	};

	Graph();
	
	NodeElement* newNode();
	EdgeElement* newEdge(NodeElement*, NodeElement*);
};

/*
 * File: ogdf/basic/GraphAttributes.h
 */
class GraphAttributes {
public:
	enum {
		nodeGraphics     = 0x00001,
		edgeGraphics     = 0x00002,
		nodeLevel        = 0x00004,
		edgeIntWeight    = 0x00008,
		edgeDoubleWeight = 0x00010,
		edgeLabel        = 0x00020,
		nodeLabel        = 0x00040,
		edgeType         = 0x00080,
		nodeType         = 0x00100,
		nodeColor        = 0x00200,
        nodeId           = 0x00400,
        edgeArrow        = 0x00800,
		edgeColor        = 0x01000,
		edgeStyle        = 0x02000,
		nodeStyle        = 0x04000,
		nodeTemplate     = 0x08000,
		edgeSubGraph     = 0x10000 
	};
	
	GraphAttributes(const Graph &G, long initAttributes = nodeGraphics | edgeGraphics);

	const DPolyline& bends(EdgeElement*) const;
	
	void addNodeCenter2Bends(int mode);
	
	const DRect boundingBox() const;
};
%extend GraphAttributes {
	void setNodeWidth(NodeElement* n, double w) {
		$self->width(n) = w;
	}
	void setNodeHeight(NodeElement* n, double h) {
		$self->height(n) = h;
	}
	void setNodeX(NodeElement* n, double a) {
		$self->x(n) = a;
	}
	void setNodeY(NodeElement* n, double b) {
		$self->y(n) = b;
	}
	void setEdgeType(EdgeElement* e, Graph::EdgeType type) {
		$self->type(e) = type;
	}
	double getNodeWidth(NodeElement* n) const {
		return $self->width(n);
	}
	double getNodeHeight(NodeElement* n) const {
		return $self->height(n);
	}
	double getNodeX(NodeElement* n) const {
		return $self->x(n);
	}
	double getNodeY(NodeElement* n) const {
		return $self->y(n);
	}
	Graph::EdgeType getEdgeType(EdgeElement* e) const {
		return $self->type(e);
	}
};

/*
 * File:ogdf/basic/UMLGraph.h
 */
class UMLGraph : public GraphAttributes {
public:
	explicit UMLGraph(Graph &G, long initAttributes = 0);
};

/*
 * File: ogdf/labeling/ELabelInterface.h
 */
enum eLabelTyp {elEnd1, elMult1, elName, elEnd2, elMult2};

/*
 * File: ogdf/labeling/ELabelInterface.h
 */
enum eUsedLabels {lName = 4, lEnd1 = 1, lMult1 = 2, lEnd2 = 8, lMult2 = 16, lAll = 31};

/*
 * File: ogdf/labeling/ELabelInterface.h
 */
template<class coordType>
class EdgeLabel {
public:
	EdgeLabel();
	
    void setX(eLabelTyp, coordType);
    void setY(eLabelTyp, coordType);
    void setHeight(eLabelTyp, coordType);
    void setWidth(eLabelTyp, coordType);
    void setEdge(EdgeElement*);
    void addType(eLabelTyp);
    coordType getX(eLabelTyp);
    coordType getY(eLabelTyp);
    coordType getWidth(eLabelTyp);
    coordType getHeight(eLabelTyp);
    EdgeElement* theEdge();
	bool usedLabel(eLabelTyp);
};
%extend EdgeLabel {
	int usedLabel() {
		// converts reference to value to avoid generation of java int-pointer class
		return $self->usedLabel();
	}
};

%template(EdgeLabelDouble) EdgeLabel<double>;

/*
 * File: ogdf/labeling/ELabelInterface.h
 */
template<class coordType>
class ELabelInterface {
public:
	ELabelInterface(GraphAttributes&);
	
	void setLabel(EdgeElement* &, const EdgeLabel<coordType> &);
	
	EdgeLabel<coordType>& getLabel(EdgeElement*);
};

%template(ELabelInterfaceDouble) ELabelInterface<double>;

/*
 * File: ogdf/labeling/ELabelPosSimple.h
 * Note: As soon as the not-simple LabelPos class is functional it should be used
 *       instead of this one.
 */
class ELabelPosSimple {
public:
	ELabelPosSimple();
	
	void call(GraphAttributes&, ELabelInterface<double>&);
};
// these getter and setter replace the public member functions of the class
%extend ELabelPosSimple {
	bool getAbsolute() const {
		return $self->m_absolut;
	}
	double getMarginDistance() const {
		return $self->m_marginDistance;
	}
	double getEdgeDistance() const {
		return $self->m_edgeDistance;
	}
	bool getMidOnEdge() const {
		return $self->m_midOnEdge;
	}
	void setAbsolute(bool absolute) {
		$self->m_absolut = absolute;
	}
	void setMarginDistance(double distance) {
		$self->m_marginDistance = distance;
	}
	void setEdgeDistance(double distance) {
		$self->m_edgeDistance = distance;
	}
	void setMidOnEdge(bool midOnEdge) {
		$self->m_midOnEdge = midOnEdge;
	}
};

/*
 * File: ogdf/module/HierarchyLayoutModule.h
 * Note: This class is abstract.
 */
// Because of SWIG's limited knowledge of the libraries internal code this method
// has to be called with parameter 'false' before an instance of this class or
// its children is set as hierarchy layout for a layouter or it will be freed 
// first on the destruction of the layouter and second by java's garbage collector
// causing at least a segmentation fault.
// This method will be automatically invoked by: SugiyamaLayout::setLayout()
%typemap(javacode) HierarchyLayoutModule %{
  void setMemOwn(boolean memOwn) {
    swigCMemOwn = memOwn;
  }
%}
class HierarchyLayoutModule {
	// this method declaration is needed so SWIG knows this class is abstract
	virtual void doCall(const Hierarchy &, GraphCopyAttributes &) = 0;
public:
	virtual ~HierarchyLayoutModule();
};

/*
 * File: ogdf/layered/FastHierarchyLayout.h
 */
class FastHierarchyLayout : public HierarchyLayoutModule {
	// this method declaration is needed so SWIG knows this class is not abstract
	virtual void doCall(const Hierarchy &, GraphCopyAttributes &);
public:
	FastHierarchyLayout();
	virtual ~FastHierarchyLayout();
	
	double nodeDistance() const;
	void nodeDistance(double);
	double layerDistance() const;
	void layerDistance(double);
	bool fixedLayerDistance() const;
	void fixedLayerDistance(bool);
};

/*
 * File: ogdf/module/LayoutModule.h
 */
class LayoutModule {
public:
	LayoutModule();
	
	virtual void call(GraphAttributes&) = 0;
};

/*
 * File: ogdf/module/UMLLayoutModule.h
 */
class UMLLayoutModule : public LayoutModule {
public:
	UMLLayoutModule();

	virtual void call(UMLGraph&) = 0;
};

/*
 * File: ogdf/layered/SugiyamaLayout.h
 */
%typemap(javacode) SugiyamaLayout %{
  public void setLayout(HierarchyLayoutModule hlm) {
    hlm.setMemOwn(false);
    setLayout_(hlm);
  }
%}
class SugiyamaLayout : public LayoutModule {
public:
	SugiyamaLayout();
	
	void call(GraphAttributes&);
	
	int fails() const;
	void fails(int);
	int runs() const;
	void runs(int);
	bool transpose() const;
	void transpose(bool);
	bool arrangeCCs() const;
	void arrangeCCs(bool);
	double minDistCC() const;
	void minDistCC(double);
	double pageRatio() const;
	void pageRatio(double);
	bool alignBaseClasses() const;
	void alignBaseClasses(bool);
	bool alignSiblings() const;
	void alignSiblings(bool);
};
%extend SugiyamaLayout {
	void setLayout_(HierarchyLayoutModule* hlm) {
		$self->setLayout(hlm);
	}
};

/*
 * File: ogdf/planarity/PlanarizationLayout.h
 */
class PlanarizationLayout : public UMLLayoutModule {
public:
	PlanarizationLayout();
	
	//TODO this requires the graph to have no self loops! what to do about that?
	void call(GraphAttributes&);
	void call(UMLGraph&);
};