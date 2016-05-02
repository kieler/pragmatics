/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2016 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kgraph.text.tests

import com.google.common.collect.Sets
import com.google.inject.Inject
import de.cau.cs.kieler.kgraph.text.grandom.GeneratorOptions
import de.cau.cs.kieler.kgraph.text.grandom.RandGraph
import de.cau.cs.kieler.kgraph.text.ui.random.GRandomGraphMaker
import java.util.ArrayList
import java.util.HashSet
import java.util.Random
import org.eclipse.elk.core.klayoutdata.KShapeLayout
import org.eclipse.elk.core.options.CoreOptions
import org.eclipse.elk.core.options.PortConstraints
import org.eclipse.elk.core.options.PortSide
import org.eclipse.elk.graph.KNode
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.junit.Test
import org.junit.runner.RunWith

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*


@RunWith(XtextRunner)
@InjectWith(GRandomInjectorProvider)
class GRandomParsingTest {

    @Inject
    ParseHelper<RandGraph> parser;

    @Test
    def givenSimpleConfig_CheckGeneratedGraph() {
        val rdg = parser.parse('''
            generate 5 graphs {
                edges total = 60
                nodes = 40
            }
        ''')
        val generator = new GRandomGraphMaker(rdg)
        val generated = generator.genKNode(rdg.configs.head)
        assertThat(generated.size, is(5))
        assertThat(generated.head.children.size, is(40))
        val edges = getAllEdges(generated.head)
        assertThat(edges.size, is(60))
    }

    @Test
    def givenSimpleConfig2_CheckGeneratedGraph() {
        val rdg = parser.parse('''
            generate 6 graphs {
                edges total = 10
                nodes = 4
            }
        ''')
        val generator = new GRandomGraphMaker(rdg)
        val generated = generator.genKNode(rdg.configs.head)
        assertThat(generated.size, is(6))
        assertThat(generated.head.children.size, is(4))
        val edges = getAllEdges(generated.head)
        assertThat(edges.size, is(10))
    }

    @Test
    def givenNoConfig_CheckDefault() {
        val rdg = parser.parse('''
            generate 1 graphs
        ''')
        val generator = new GRandomGraphMaker(rdg)
        val generated = generator.genKNode(rdg.configs.head)
        assertThat(generated.size, is(1))
        assertThat(generated.head.children.size, is(10))
        val edges = getAllEdges(generated.head)
        assertThat(edges.size, is(20))
        assertTrue(edges.head.labels.empty)
        assertTrue(generated.head.children.head.labels.empty)
    }

    @Test
    def givenSeed_CheckSameGraphsGenerated() {
        val rdg = parser.parse('''
            generate 20 graphs {
                nodes = 1 to 1000
                seed = 0
            }
        ''')
        val generator1 = new GRandomGraphMaker(rdg)
        val generated1 = generator1.genKNode(rdg.configs.head)
        val generator2 = new GRandomGraphMaker(rdg)
        val generated2 = generator2.genKNode(rdg.configs.head)
        assertThat(generated1.map[it.children.size], is(generated2.map[it.children.size]))
    }

    def getEdges(KNode generated) {
        val edges = new HashSet
        edges.addAll(generated.incomingEdges)
        edges.addAll(generated.outgoingEdges)
        edges.toList
    }

    @Test
    def givenRelativeEdges_CheckGeneratedGraph() {
        val rdg = parser.parse('''
            generate 4 graphs {
               nodes = 30
               edges relative = 1
            }
        ''')

        val generator = new GRandomGraphMaker(rdg)
        val generated = generator.genKNode(rdg.configs.head)
        assertThat(generated.size, is(4))
        assertThat(generated.head.children.size, is(30))
        val edges = getAllEdges(generated.head)
        assertThat(edges.size, is(30))
    }

    def getAllEdges(KNode node) {
        return node.children.map[edges].flatten.toSet
    }

    @Test
    def void givenRandom_CheckBorders() {
        val rdg = parser.parse('''
            generate 200 graphs {
               nodes = 10 to 20
               seed = 0
            }
        ''')
        val generator = new GRandomGraphMaker(rdg)
        val generated = generator.genKNode(rdg.configs.head)
        assertThat(generated.size, is(200))
        val sizes = new ArrayList
        for (gen : generated) {
            val size = gen.children.size
            assertTrue(size <= 20 && size >= 10)
            sizes.add(size)
        }
        assertTrue(sizes.stream.anyMatch[it != sizes.head])
    }

    @Test
    def void givenNormalDistribution_Check() {
        val rdg = parser.parse('''
            generate 20 graphs {
               nodes = 100 +/- 5

               seed = 1
            }
        ''')
        val generator = new GRandomGraphMaker(rdg)
        val generated = generator.genKNode(rdg.configs.head)
        val sizes = new ArrayList
        for (gen : generated) {
            val size = gen.children.size
            sizes.add(size)
        }
        assertTrue(sizes.map[it != sizes.head].any)
        val mean = sizes.stream.mapToDouble[it].average.asDouble
        assertTrue(mean < 110 && mean > 90)
        val stdv = Math.sqrt(sizes.stream.mapToDouble[it].map([b|Math.pow(b - mean, 2)]).average.asDouble)
        assertTrue(stdv < 6 && stdv > 4)
    }

    @Test
    def void givenNormalDistributionEdges_Check() {
        val rdg = parser.parse('''
            generate 1 graphs {
               nodes = 100 {
                }
               edges outgoing = 100 +/- 5
            }
        ''')
        val generator = new GRandomGraphMaker(rdg)
        val generated = generator.genKNode(rdg.configs.head)
        val sizes = generated.head.children.map[outgoingEdges].map[size]
        val mean = sizes.stream.mapToDouble[it].average.asDouble
        assertTrue(mean.toString, mean < 110 && mean > 90)
        val stdv = Math.sqrt(sizes.stream.mapToDouble[it].map([b|Math.pow(b - mean, 2)]).average.asDouble)
        assertTrue(stdv.toString, stdv < 7 && stdv > 3)
    }

    @Test
    def void givenTypes_CheckWhetherOptionSet() {
        val types = newArrayList('trees', 'bipartite graphs', 'graphs', 'triconnected graphs',
            'biconnected graphs', 'acyclic graphs')
        val generatedTypes = new ArrayList
        for (type : types){
            val rdg = parser.parse('''
                generate «type»
            ''')
            val generator = new GRandomGraphMaker(rdg)
            val config = rdg.configs.head
            val generated = generator.parseGenOptions(config, new Random)
            generatedTypes.add(generated.getProperty(GeneratorOptions.GRAPH_TYPE))
        }
        assertTrue(generatedTypes.forall[o | generatedTypes.filter[it == o].size == 1])
    }

    @Test
    def givenLabels_CheckHasLabels() {
        val rdg = parser.parse('''
            generate 1 graphs {
                nodes = 20 {
                    labels
                }
                edges total = 10 {
                    labels
                }
            }
        ''')
        val generator = new GRandomGraphMaker(rdg)
        val generated = generator.genKNode(rdg.configs.head)
        val edges = getAllEdges(generated.head)
        assertThat(edges.size, is(10))
        assertFalse(edges.head.labels.empty)
        assertFalse(generated.head.children.head.labels.empty)
    }

    @Test
    def givenPorts_CheckHasPorts() {
        val rdg = parser.parse('''
            generate 1 graphs {
                    edges relative = 1
                nodes = 20 {
                    ports
                }
                seed 1
            }
        ''')
        assertNotNull(rdg.configs.head.nodes.labels)
        val generator = new GRandomGraphMaker(rdg)
        val generated = generator.genKNode(rdg.configs.head)
        assertTrue(generated.head.children.map[!it.ports.empty].any)
    }

    def boolean any(Iterable<Boolean> booleans) {
        var any = false
        for (boolean b : booleans) {
            any = any || b
        }
        any
    }

    @Test
    def givenPortsWithLabels_HasLabels() {
        val rdg = parser.parse('''
            generate 1 graphs {
                edges relative = 1
                nodes = 20 {
                    ports {
                        labels
                    }
                }
            }
        ''')
        val generator = new GRandomGraphMaker(rdg)
        val generated = generator.genKNode(rdg.configs.head)
        val nodesWithports = generated.head.children.filter[!it.ports.empty]
        assertFalse(nodesWithports.head.ports.head.labels.empty)
    }

    @Test
    def givenPortsWithoutLabels_HasNone() {
        val rdg = parser.parse('''
            generate 1 graphs {
                edges relative = 1
                nodes = 20 {
                    ports
                }
            }
        ''')
        val generator = new GRandomGraphMaker(rdg)
        val generated = generator.genKNode(rdg.configs.head)
        val nodesWithports = generated.head.children.filter[!it.ports.empty]
        assertTrue(nodesWithports.head.ports.head.labels.empty)
    }

    @Test
    def givenSize_Check() {
        val rdg = parser.parse('''
            generate 1 graphs {
                nodes = 20 {
                    size {
                        height = 1
                        width = 1
                    }
                }
            }
        ''')
        val generator = new GRandomGraphMaker(rdg)
        val generated = generator.genKNode(rdg.configs.head)
        assertThat(generated.head.children.head.getData(KShapeLayout).width, is(1.0f))
        assertThat(generated.head.children.head.getData(KShapeLayout).height, is(1.0f))
    }
    @Test
    def givenPortSize_Check() {
        val rdg = parser.parse('''
            generate 1 graphs {
                nodes = 20 {
                    ports {
                        size {
                            width = 2
                            height = 1
                        }
                    }
                }
            }
        ''')
        val generator = new GRandomGraphMaker(rdg)
        val generated = generator.genKNode(rdg.configs.head)
        val ports = generated.head.children.filter[!ports.empty].map[ports].flatten
        assertTrue(ports.forall[it.getData(KShapeLayout).height == 1.0f])
        assertTrue(ports.forall[it.getData(KShapeLayout).width == 2.0f])
    }

    @Test
    def givenPortConstraint_Check() {
        val rdg = parser.parse('''
            generate 1 graphs {
                nodes = 20 {
                    ports {
                        constraint = position
                    }
                }
            }
        ''')
        val generator = new GRandomGraphMaker(rdg)
        val generated = generator.genKNode(rdg.configs.head)
        val constraint = generated.head.children.head.getData(KShapeLayout).getProperty(CoreOptions.PORT_CONSTRAINTS)
        assertThat(constraint, is(PortConstraints.FIXED_POS))
    }

    @Test
    def givenRandomness_Check() {
        val rdg = parser.parse('''
            generate 1 graphs {
                edges outgoing = 1 to 10
                nodes = 20 {
                    size {
                        height = 10 to 50
                        width = 10 to 50
                    }
                }
                seed = 1
            }
        ''')
        val generator = new GRandomGraphMaker(rdg)
        val generated = generator.genKNode(rdg.configs.head)
        val edgeSizes = generated.head.children.map[getEdges].map[size]
        assertTrue(edgeSizes.map[it != edgeSizes.head].any)
        val outgoingEdges = generated.head.children.map[outgoingEdges].map[size]
        assertTrue(outgoingEdges.forall[it <= 10 && it >= 1])
        val nodeSizes = generated.head.children.map[getData(KShapeLayout).width]
        assertTrue(nodeSizes.map[it != nodeSizes.head].any)
    }

    @Test
    def givenFlowDirectionAndSide_Check() {
        val rdg = parser.parse('''
            generate 1 graphs {
                nodes = 20 {
                    ports {
                        constraint = order
                        outgoing north =  0
                        outgoing east  = 100
                        outgoing south =  0
                        outgoing west  = 0
                        incoming north =  0
                        incoming east  = 100
                        incoming south =  0
                        incoming west  = 0
                    }
                }
            }
        ''')
        val generator = new GRandomGraphMaker(rdg)
        val generated = generator.genKNode(rdg.configs.head)
        val ports = generated.head.children.map[ports].flatten
        assertTrue(ports.forall[getData(KShapeLayout).getProperty(CoreOptions.PORT_SIDE) == PortSide.EAST])
    }

    @Test
    def givenHierarchical_CheckHasHierarchical() {
        val rdg = parser.parse('''
            generate 1 graphs {
                nodes = 1
                hierarchy{
                      compound nodes = 1
                }
            }
        ''')
        val generator = new GRandomGraphMaker(rdg)
        val generated = generator.genKNode(rdg.configs.head)
        assertTrue(generated.head.children.map[children.size > 0].any)
        assertThat(generated.head.children.size, is(2));
        assertTrue(generated.head.children.map[children].forall[size==2 || size == 0]);
    }

    @Test
    def givenHierarchical_CheckNoHierarchicalOnLowestLayer() {
        val rdg = parser.parse('''
            generate 1 graphs {
                nodes = 1 {
                    labels
                    }
                
                hierarchy {
                    compound nodes = 1
                    levels = 2
                }
            }
        ''')
        val generator = new GRandomGraphMaker(rdg)
        val generated = generator.genKNode(rdg.configs.head)
        assertThat(generated.head.children.size, is(2))
        val hierarchicalNodes = generated.head.children.filter[children.size > 0]
        assertThat(hierarchicalNodes.head.children.size, is(1))
    }

    @Test
    def givenCrossHierarchicalEdges_CheckExist() {
        val rdg = parser.parse('''
            generate 1 graphs {
                nodes = 1 {
                    labels
                    }
                hierarchy {
                    compound nodes = 1
                    cross-hierarchy edges = 5
                }
            }
        ''')
        val generator = new GRandomGraphMaker(rdg)
        val generated = generator.genKNode(rdg.configs.head)
        val parentGraphNodes = generated.head.children
        val edgeSourcesAndTargets = Sets.newHashSet(parentGraphNodes.map[edges].flatten.map[source])
        edgeSourcesAndTargets.addAll(Sets.newHashSet(parentGraphNodes.map[edges].flatten.map[target]))
        assertTrue(edgeSourcesAndTargets.map[!parentGraphNodes.contains(it)].any)
    }
    
    @Test
    def void givenMulitLevelHierarch_CheckSameLevelEverywhere() {
        val rdg = parser.parse('''
            generate 1 graphs {
                nodes = 1 {
                    labels
                }
                hierarchy {
                    cross-hierarchy edges = 5.0
                    compound nodes = 1.0
                    levels = 3
                }
            }
        ''')
        val generator = new GRandomGraphMaker(rdg)
        val generated = generator.genKNode(rdg.configs.head)
        val parentGraphNodes = generated.head.children
        val hierarchicalGraphNodes = parentGraphNodes.filter[children.size > 1]
        val secondLevelHierarchNodes = hierarchicalGraphNodes.map[children].flatten
        assertThat(secondLevelHierarchNodes.size, is(2))
    }

    @Test
    def void givenHierarchRandom_CheckRandomnessDifferentOnEach() {
        val rdg = parser.parse('''
            generate 1 graphs {
                nodes = 1 to 1000 {
                    labels
                }
                hierarchy {
                    compound nodes = 1
                }
                seed = 0
            }
        ''')
        val generator = new GRandomGraphMaker(rdg)
        val generated = generator.genKNode(rdg.configs.head)
        val parentGraphNodes = generated.head.children
        val parentSize = parentGraphNodes.size 
        val levelBelowSize = parentGraphNodes.filter[children.size > 0].head.children.size 
        assertThat(parentSize, not(is(levelBelowSize)))
    }

}
