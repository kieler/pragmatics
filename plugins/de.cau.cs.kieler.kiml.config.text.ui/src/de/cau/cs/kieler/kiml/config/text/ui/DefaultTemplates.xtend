package de.cau.cs.kieler.kiml.config.text.ui

import java.util.List
import com.google.common.collect.ImmutableList

class DefaultTemplates {
    
    public static final List<Pair<String, String>> defaultTemplates = ImmutableList.of(
        Pair.of(
        "Libavoid", 
        '''
        Libavoid {
            algorithm: org.adaptagrams.cola.libavoid
            edgeRouting: ORTHOGONAL 
        }
        '''),
        Pair.of(
        "WebCola+Libavoid", 
        '''
        Webcola {
            algorithm: org.adaptagrams.cola.webcola.constraintLayout
        }
        Libavoid {
            algorithm: org.adaptagrams.cola.libavoid
            edgeRouting: ORTHOGONAL 
        }
        ''')
    )
    
    
    
}