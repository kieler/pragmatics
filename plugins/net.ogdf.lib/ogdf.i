%module Ogdf
%{

/*---     Header code directly copied to ogdf_wrap.cpp      ---*
 *-------------------------------------------------------------*/

#include <ogdf/basic/geometry.h>
#include <ogdf/basic/Graph_d.h>
#include <ogdf/basic/GraphAttributes.h>
#include <ogdf/basic/UMLGraph.h>
#include <ogdf/labeling/ELabelInterface.h>
#include <ogdf/labeling/ELabelPosSimple.h>
#include <ogdf/module/HierarchyLayoutModule.h>
#include <ogdf/layered/FastHierarchyLayout.h>
#include <ogdf/module/PlanarSubgraphModule.h>
#include <ogdf/planarity/FastPlanarSubgraph.h>
#include <ogdf/module/LayoutPlanRepModule.h>
#include <ogdf/orthogonal/OrthoRep.h>
#include <ogdf/orthogonal/OrthoLayout.h>
#include <ogdf/module/LayoutModule.h>
#include <ogdf/module/UMLLayoutModule.h>
#include <ogdf/layered/SugiyamaLayout.h>
#include <ogdf/misclayout/BalloonLayout.h>
#include <ogdf/planarity/PlanarizationLayout.h>

using namespace ogdf;

%}


/*---         General configuration and extensions          ---*
 *-------------------------------------------------------------*/

%include "enums.swg"
%javaconst(1);

%pragma(java) jniclasscode=%{
    /**
     * Load the OGDF dynamic library for access to the Java-side API.
     * 
     * @throws java.io.IOException if loading the library fails
     */
    public static void loadLibrary() throws java.io.IOException {
        try {
            System.loadLibrary("ogdf");
        } catch (UnsatisfiedLinkError error) {
            throw new java.io.IOException("Failed to load OGDF library.", error);
        } catch (SecurityException exception) {
            throw new java.io.IOException("Failed to load OGDF library.", exception);
        }
    }
%}


/*---      Specification headers for wrapper interface      ---*
 *-------------------------------------------------------------*/


/*****
 * File: ogdf/basic/List.h
 */

// provide Java iterator support for list iterators: add interface declaration
%typemap(javainterfaces) ListConstIterator<DPoint> %{ Iterator<DPoint> %}
%typemap(javaimports) ListConstIterator<DPoint> %{ import java.util.Iterator; %}

template<class E> class ListConstIterator {
public:
	ListConstIterator();
};
// provide Java iterator support for list iterators: add missing methods
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

// create concrete instances of template class
%template(DPointListConstIterator) ListConstIterator<DPoint>;
%template(EdgeElementListConstIterator) ListConstIterator<EdgeElement*>;

// provide Java iteration support for lists: add interface declaration
%typemap(javainterfaces) List<DPoint> %{ Iterable<DPoint> %}

template<class E> class List {
public:
	List();

	bool empty() const;
	int size() const;
};
// provide Java iteration support for lists: add missing methods
%extend List {
	const ListConstIterator<E> iterator() const {
		return $self->begin();
	}
};

// create concrete instances of template class
%template(DPointList) List<DPoint>;
%template(EdgeElementList) List<EdgeElement*>;


/*****
 * File: ogdf/basic/geometry.h
 */

enum Orientation {
	topToBottom,
	bottomToTop,
	leftToRight,
	rightToLeft
};

class DPoint {
public:
	DPoint();
	DPoint(double, double);
};
// add getters for the point coordinate values
%extend DPoint {
	double getX() const {
		return $self->m_x;
	}
	double getY() const {
		return $self->m_y;
	}
};

class DRect {
public:
	DRect();
	DRect(const DPoint &p1, const DPoint &p2);
	DRect(double x1, double y1, double x2, double y2);
	
	double width() const;
	double height() const;
	
	const DPoint &p1() const;
	const DPoint &p2() const;
	
	bool contains(const DPoint &p) const;
};

class DPolyline : public List<DPoint> {
	DPolyline();
};


/*****
 * File: ogdf/basic/Graph_d.h
 */

class NodeElement {
	NodeElement(int id);
};

class EdgeElement {
	EdgeElement(NodeElement* src, NodeElement* tgt, int id);
};

class Graph {
public:
	enum EdgeType {
		association = 0,
		generalization = 1,
		dependency = 2
	};

	Graph();
	
	NodeElement* newNode();
	EdgeElement* newEdge(NodeElement* v, NodeElement* w);
};


/*****
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
		edgeSubGraph     = 0x10000,
		nodeWeight       = 0x20000
	};

	enum {
		oval      = 0x8001,
		rectangle = 0x8002
	};
	
	GraphAttributes(const Graph &G, long initAttributes = nodeGraphics | edgeGraphics);

	const DPolyline& bends(EdgeElement*) const;
	
	void addNodeCenter2Bends(int mode);
	
	const DRect boundingBox() const;
};
// add getters and setters for the graph attribute values
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


/*****
 * File: ogdf/basic/UMLGraph.h
 */

class UMLGraph : public GraphAttributes {
public:
	explicit UMLGraph(Graph &G, long initAttributes = 0);
};


/*****
 * File: ogdf/labeling/ELabelInterface.h
 */

enum eLabelTyp {elEnd1, elMult1, elName, elEnd2, elMult2};

enum eUsedLabels {lName = 4, lEnd1 = 1, lMult1 = 2, lEnd2 = 8, lMult2 = 16, lAll = 31};

template<class coordType> class EdgeLabel {
public:
	EdgeLabel();
	
	void setX(eLabelTyp elt, coordType x);
	void setY(eLabelTyp elt, coordType y);
	void setHeight(eLabelTyp elt, coordType h);
	void setWidth(eLabelTyp elt, coordType w);
	void setEdge(EdgeElement* e);
	void addType(eLabelTyp elt);

	coordType getX(eLabelTyp elt);
	coordType getY(eLabelTyp elt);
	coordType getWidth(eLabelTyp elt);
	coordType getHeight(eLabelTyp elt);
	EdgeElement* theEdge();
	bool usedLabel(eLabelTyp elt);
};
// convert reference to value to avoid generation of java int-pointer class
%extend EdgeLabel {
	int usedLabel() {
		return $self->usedLabel();
	}
};

// create concrete instance of template class
%template(EdgeLabelDouble) EdgeLabel<double>;

template<class coordType> class ELabelInterface {
public:
	ELabelInterface(GraphAttributes&);
	
	void setLabel(EdgeElement* &, const EdgeLabel<coordType> &);
	
	EdgeLabel<coordType>& getLabel(EdgeElement*);
};

// create concrete instance of template class
%template(ELabelInterfaceDouble) ELabelInterface<double>;


/*****
 * File: ogdf/labeling/ELabelPosSimple.h
 */

class ELabelPosSimple {
public:
	ELabelPosSimple();
	
	void call(GraphAttributes&, ELabelInterface<double>&);
};
// replace public members by according getters and setters
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

/*****
 * File: ogdf/module/HierarchyLayoutModule.h
 * Note: This class is abstract.
 */

// Because of SWIG's limited knowledge of the libraries internal code this method
// has to be called with parameter 'false' before an instance of this class or
// its children is set as module for a layouter or it will be freed 
// first on the destruction of the layouter and second by java's garbage collector
// causing at least a segmentation fault.
// This method will be automatically invoked by: SugiyamaLayout::setLayout()
// TODO: Is this directly configurable by SWIG?
%typemap(javacode) HierarchyLayoutModule %{
  void setMemOwn(boolean memOwn) {
    swigCMemOwn = memOwn;
  }
%}
class HierarchyLayoutModule {
	// this method declaration is needed so SWIG knows this class is abstract
	//virtual void doCall(const Hierarchy &, GraphCopyAttributes &) = 0;
	virtual void dummy() = 0;
public:
	virtual ~HierarchyLayoutModule();
};

/*****
 * File: ogdf/layered/FastHierarchyLayout.h
 */

class FastHierarchyLayout : public HierarchyLayoutModule {
	// this method declaration is needed so SWIG knows this class is not abstract
	virtual void dummy();
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

/*****
 * File: ogdf/module/PlanarSubgraphModule.h
 */

// Because of SWIG's limited knowledge of the libraries internal code this method
// has to be called with parameter 'false' before an instance of this class or
// its children is set as module for a layouter or it will be freed 
// first on the destruction of the layouter and second by java's garbage collector
// causing at least a segmentation fault.
// This method will be automatically invoked by: PlanarizationLayout::setSubgraph()
%typemap(javacode) PlanarSubgraphModule %{
  void setMemOwn(boolean memOwn) {
    swigCMemOwn = memOwn;
  }
%}
class PlanarSubgraphModule {
	// this method declaration is needed so SWIG knows this class is abstract
	virtual void dummy() = 0;
public:
	PlanarSubgraphModule();
};

/*****
 * File: ogdf/planarity/FastPlanarSubgraph.h
 */

class FastPlanarSubgraph : public PlanarSubgraphModule {
	// this method declaration is needed so SWIG knows this class is not abstract
	virtual void dummy();
public:
	FastPlanarSubgraph();
	
	int runs() const;
	void runs(int runs);
};

/*****
 * File: ogdf/module/LayoutPlanRepModule.h
 */
// Because of SWIG's limited knowledge of the libraries internal code this method
// has to be called with parameter 'false' before an instance of this class or
// its children is set as module for a layouter or it will be freed 
// first on the destruction of the layouter and second by java's garbage collector
// causing at least a segmentation fault.
// This method will be automatically invoked by: PlanarizationLayout::setPlanarLayouter()
%typemap(javacode) LayoutPlanRepModule %{
  void setMemOwn(boolean memOwn) {
    swigCMemOwn = memOwn;
  }
%}
class LayoutPlanRepModule {
	// this method declaration is needed so SWIG knows this class is abstract
	virtual void dummy() = 0;
public:
	LayoutPlanRepModule();
};

/*****
 * File: ogdf/orthogonal/OrthoRep.h
 */
enum OrthoDir {
	odNorth     = 0,
	odEast      = 1,
	odSouth     = 2,
	odWest      = 3,
	odUndefined = 4
};

/*****
 * File: ogdf/orthogonal/OrthoLayout.h
 */

class OrthoLayout : public LayoutPlanRepModule {
	// this method declaration is needed so SWIG knows this class is not abstract
	virtual void dummy();
public:
	OrthoLayout();
	
	double separation() const;
	void separation(double);
	double cOverhang() const;
	void cOverhang(double);
	double margin() const;
	void margin(double);
	OrthoDir preferedDir() const;
	void preferedDir(OrthoDir);
	int costAssoc() const;
	void costAssoc(int);
	int costGen() const;
	void costGen(int);
};

/*****
 * File: ogdf/module/LayoutModule.h
 */

class LayoutModule {
public:
	LayoutModule();
	
	virtual void call(GraphAttributes&) = 0;
};

/*****
 * File: ogdf/module/UMLLayoutModule.h
 */

class UMLLayoutModule : public LayoutModule {
public:
	UMLLayoutModule();

	virtual void call(UMLGraph&) = 0;
};

/*****
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

/*****
 * File: ogdf/planarity/PlanarizationLayout.h
 */

%typemap(javacode) PlanarizationLayout %{
  public void setSubgraph(PlanarSubgraphModule psm) {
    psm.setMemOwn(false);
    setSubgraph_(psm);
  }
  public void setPlanarLayouter(LayoutPlanRepModule lprm) {
	lprm.setMemOwn(false);
	setPlanarLayouter_(lprm);
  }
%}
class PlanarizationLayout : public UMLLayoutModule {
public:
	PlanarizationLayout();
	
	void call(GraphAttributes&);
	void call(UMLGraph&);
	
	double pageRatio() const;
	void pageRatio(double);
	bool preprocessCliques() const;
	void preprocessCliques(bool);
	int minCliqueSize() const;
	void minCliqueSize(int);
};
%extend PlanarizationLayout {
	void setSubgraph_(PlanarSubgraphModule* psm) {
		$self->setSubgraph(psm);
	}
	void setPlanarLayouter_(LayoutPlanRepModule* lprm) {
		$self->setPlanarLayouter(lprm);
	}
};
