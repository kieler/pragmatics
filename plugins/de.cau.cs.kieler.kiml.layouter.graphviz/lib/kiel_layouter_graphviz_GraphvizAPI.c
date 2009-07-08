#include <jni.h>
#include <dotneato.h>
#include <string.h>
#include "kiel_layouter_graphviz_GraphvizAPI.h"

extern char* Info[];

/*
 * Class:     kiel_layouter_graphviz_GraphvizAPI
 * Method:    createGraph
 * Signature: (Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL
  Java_kiel_layouter_graphviz_GraphvizAPI_createGraph
    (JNIEnv *env, jclass obj, jstring name)
{
    Agraph_t *g;
    const char *c_constname = (*env)->GetStringUTFChars(env, name, 0);
    char *c_name = malloc((strlen(c_constname) + 1) * sizeof(char));

    strcpy(c_name, c_constname);
    g = agopen(c_name, AGDIGRAPH);
    dot_init_graph(g);

    return (jint) g;
}    

/*
 * Class:     kiel_layouter_graphviz_GraphvizAPI
 * Method:    createNode
 * Signature: (ILjava/lang/String;)I
 */
JNIEXPORT jint JNICALL
  Java_kiel_layouter_graphviz_GraphvizAPI_createNode
    (JNIEnv *env, jclass obj, jint graph, jstring name)
{
    Agnode_t *node;
    const char *c_constname = (*env)->GetStringUTFChars(env, name, 0);
    char *c_name = malloc((strlen(c_constname) + 1) * sizeof(char));

    strcpy(c_name, c_constname);
    node = agnode((Agraph_t*) graph, c_name);
    
    return (jint) node;
}    

/*
 * Class:     kiel_layouter_graphviz_GraphvizAPI
 * Method:    createEdge
 * Signature: (III)I
 */
JNIEXPORT jint JNICALL
  Java_kiel_layouter_graphviz_GraphvizAPI_createEdge
    (JNIEnv *env, jclass obj, jint graph, jint source, jint target)
{
    Agedge_t *edge;

    edge = agedge((Agraph_t*) graph, (Agnode_t*) source,
                  (Agnode_t*) target);

    return (jint) edge;
}    

/*
 * Class:     kiel_layouter_graphviz_GraphvizAPI
 * Method:    doDotLayout
 * Signature: (I)V
 */
JNIEXPORT void JNICALL
  Java_kiel_layouter_graphviz_GraphvizAPI_doDotLayout
    (JNIEnv *env, jclass obj, jint graph)
{
    dot_layout((Agraph_t*) graph);
}

/*
 * Class:     kiel_layouter_graphviz_GraphvizAPI
 * Method:    doNeatoLayout
 * Signature: (I)V
 */
JNIEXPORT void JNICALL
  Java_kiel_layouter_graphviz_GraphvizAPI_doNeatoLayout
    (JNIEnv *env, jclass obj, jint graph)
{
    neato_layout((Agraph_t*) graph);
}

/*
 * Class:     kiel_layouter_graphviz_GraphvizAPI
 * Method:    doTwopiLayout
 * Signature: (I)V
 */
JNIEXPORT void JNICALL
  Java_kiel_layouter_graphviz_GraphvizAPI_doTwopiLayout
    (JNIEnv *env, jclass obj, jint graph)
{
    twopi_layout((Agraph_t*) graph);
}

/*
 * Class:     kiel_layouter_graphviz_GraphvizAPI
 * Method:    doCircoLayout
 * Signature: (I)V
 */
JNIEXPORT void JNICALL
  Java_kiel_layouter_graphviz_GraphvizAPI_doCircoLayout
    (JNIEnv *env, jclass obj, jint graph)
{
    circo_layout((Agraph_t*) graph);
}

/*
 * Class:     kiel_layouter_graphviz_GraphvizAPI
 * Method:    closeGraph
 * Signature: (I)V
 */
JNIEXPORT void JNICALL
  Java_kiel_layouter_graphviz_GraphvizAPI_closeGraph
    (JNIEnv *env , jclass obj, jint graph)
{
    agclose((Agraph_t*) graph);
}

/*
 * Class:     kiel_layouter_graphviz_GraphvizAPI
 * Method:    dotCleanup
 * Signature: (I)V
 */
JNIEXPORT void JNICALL
  Java_kiel_layouter_graphviz_GraphvizAPI_dotCleanup
    (JNIEnv *env , jclass obj, jint graph)
{
    dot_cleanup((Agraph_t*) graph);
}

/*
 * Class:     kiel_layouter_graphviz_GraphvizAPI
 * Method:    neatoCleanup
 * Signature: (I)V
 */
JNIEXPORT void JNICALL
  Java_kiel_layouter_graphviz_GraphvizAPI_neatoCleanup
    (JNIEnv *env , jclass obj, jint graph)
{
    neato_cleanup((Agraph_t*) graph);
}

/*
 * Class:     kiel_layouter_graphviz_GraphvizAPI
 * Method:    twopiCleanup
 * Signature: (I)V
 */
JNIEXPORT void JNICALL
  Java_kiel_layouter_graphviz_GraphvizAPI_twopiCleanup
    (JNIEnv *env , jclass obj, jint graph)
{
    twopi_cleanup((Agraph_t*) graph);
}

/*
 * Class:     kiel_layouter_graphviz_GraphvizAPI
 * Method:    circoCleanup
 * Signature: (I)V
 */
JNIEXPORT void JNICALL
  Java_kiel_layouter_graphviz_GraphvizAPI_circoCleanup
    (JNIEnv *env , jclass obj, jint graph)
{
    circo_cleanup((Agraph_t*) graph);
}

/*
 * Class:     kiel_layouter_graphviz_GraphvizAPI
 * Method:    setGraphAttribute
 * Signature: (ILjava/lang/String;Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL
  Java_kiel_layouter_graphviz_GraphvizAPI_setGraphAttribute
    (JNIEnv *env, jclass obj, jint graph, jstring attr, jstring value)
{
    const char *c_constattr = (*env)->GetStringUTFChars(env, attr, 0);
    char *c_attr = malloc((strlen(c_constattr) + 1) * sizeof(char));
    const char *c_constvalue = (*env)->GetStringUTFChars(env, value, 0);
    char *c_value = malloc((strlen(c_constvalue) + 1) * sizeof(char));

    strcpy(c_attr, c_constattr);
    strcpy(c_value, c_constvalue);
    agraphattr((Agraph_t*) graph, c_attr, c_value);
}
/*
 * Class:     kiel_layouter_graphviz_GraphvizAPI
 * Method:    setGlobalNodeAttribute
 * Signature: (ILjava/lang/String;Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL
  Java_kiel_layouter_graphviz_GraphvizAPI_setGlobalNodeAttribute
    (JNIEnv *env, jclass obj, jint graph, jstring attr, jstring value)
{
    const char *c_constattr = (*env)->GetStringUTFChars(env, attr, 0);
    char *c_attr = malloc((strlen(c_constattr) + 1) * sizeof(char));
    const char *c_constvalue = (*env)->GetStringUTFChars(env, value, 0);
    char *c_value = malloc((strlen(c_constvalue) + 1) * sizeof(char));

    strcpy(c_attr, c_constattr);
    strcpy(c_value, c_constvalue);
    agnodeattr((Agraph_t*) graph, c_attr, c_value);
}

/*
 * Class:     kiel_layouter_graphviz_GraphvizAPI
 * Method:    setLocalNodeAttribute
 * Signature: (IILjava/lang/String;Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL 
  Java_kiel_layouter_graphviz_GraphvizAPI_setLocalNodeAttribute
    (JNIEnv *env, jclass obj, jint graph,
     jint node, jstring attr, jstring value)
{
    const char *c_constattr = (*env)->GetStringUTFChars(env, attr, 0);
    char *c_attr = malloc((strlen(c_constattr) + 1) * sizeof(char));
    const char *c_constvalue = (*env)->GetStringUTFChars(env, value, 0);
    char *c_value = malloc((strlen(c_constvalue) + 1) * sizeof(char));

    strcpy(c_attr, c_constattr);
    strcpy(c_value, c_constvalue);
    if (agfindattr((void*) graph, c_attr) == 0) {
        agnodeattr((Agraph_t*) graph, c_attr, "");
    }
    agset((void*) node, c_attr, c_value);
}

/*
 * Class:     kiel_layouter_graphviz_GraphvizAPI
 * Method:    setGlobalEdgeAttribute
 * Signature: (ILjava/lang/String;Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL
   Java_kiel_layouter_graphviz_GraphvizAPI_setGlobalEdgeAttribute
    (JNIEnv *env, jclass obj, jint graph,
     jstring attr, jstring value)
{
    const char *c_constattr = (*env)->GetStringUTFChars(env, attr, 0);
    char *c_attr = malloc((strlen(c_constattr) + 1) * sizeof(char));
    const char *c_constvalue = (*env)->GetStringUTFChars(env, value, 0);
    char *c_value = malloc((strlen(c_constvalue) + 1) * sizeof(char));

    strcpy(c_attr, c_constattr);
    strcpy(c_value, c_constvalue);
    agedgeattr((Agraph_t*) graph, c_attr, c_value);
}

/*
 * Class:     kiel_layouter_graphviz_GraphvizAPI
 * Method:    setLocalEdgeAttribute
 * Signature: (IILjava/lang/String;Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL
   Java_kiel_layouter_graphviz_GraphvizAPI_setLocalEdgeAttribute
    (JNIEnv *env, jclass obj, jint graph,
     jint edge, jstring attr, jstring value)
{
    const char *c_constattr = (*env)->GetStringUTFChars(env, attr, 0);
    char *c_attr = malloc((strlen(c_constattr) + 1) * sizeof(char));
    const char *c_constvalue = (*env)->GetStringUTFChars(env, value, 0);
    char *c_value = malloc((strlen(c_constvalue) + 1) * sizeof(char));

    strcpy(c_attr, c_constattr);
    strcpy(c_value, c_constvalue);
    if (agfindattr((void*) graph, c_attr) == 0) {
        agedgeattr((Agraph_t*) graph, c_attr, "");
    }
    agset((void*) edge, c_attr, c_value);
}

/*
 * Class:     kiel_layouter_graphviz_GraphvizAPI
 * Method:    getAttribute
 * Signature: (ILjava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL
  Java_kiel_layouter_graphviz_GraphvizAPI_getAttribute
    (JNIEnv *env, jclass obj, jint elem, jstring attr)
{
    const char *c_constattr = (*env)->GetStringUTFChars(env, attr, 0);
    char *c_attr = malloc((strlen(c_constattr) + 1) * sizeof(char));
    char *c_value;

    strcpy(c_attr, c_constattr);
    c_value = agget((void*) elem, c_attr);

    return (*env)->NewStringUTF(env, c_value);
}

/*
 * Class:     kiel_layouter_graphviz_GraphvizAPI
 * Method:    attachAttributes
 * Signature: (I)V
 */
JNIEXPORT void JNICALL
  Java_kiel_layouter_graphviz_GraphvizAPI_attachAttributes
    (JNIEnv *env, jclass obj, jint graph)
{
    attach_attrs((Agraph_t*) graph);
}

/*
 * Class:     kiel_layouter_graphviz_GraphvizAPI
 * Method:    writeDOT
 * Signature: (ILjava/lang/String;)V
 */
JNIEXPORT void JNICALL
  Java_kiel_layouter_graphviz_GraphvizAPI_writeDOT
    (JNIEnv *env, jclass obj, jint graph, jstring filename)
{
    const char *c_constfilename = (*env)->GetStringUTFChars(env, filename, 0);
    char *c_filename = malloc((strlen(c_constfilename) + 1) * sizeof(char));
    FILE *file;

    strcpy(c_filename, c_constfilename);
    file = fopen(c_filename, "w");

    agwrite((Agraph_t*) graph, file);
    fclose(file);
}

/*
 * Class:     kiel_layouter_graphviz_GraphvizAPI
 * Method:    getVersionString
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL
  Java_kiel_layouter_graphviz_GraphvizAPI_getVersionString
    (JNIEnv *env, jclass obj)
{
    return (*env)->NewStringUTF(env, Info[1]);
}

/*
 * Class:     kiel_layouter_graphviz_GraphvizAPI
 * Method:    initialize
 * Signature: ()V
 */
JNIEXPORT void JNICALL
  Java_kiel_layouter_graphviz_GraphvizAPI_initialize
    (JNIEnv *env, jclass obj) {
    aginit();
}
