#------------------------------------------------------------------------------
# Makefile for compilation of OGDF as shared library with JNI wrapper for use
# in Java applications. This requires SWIG to be installed:
#     http://www.swig.org/
#
# Initial version: 9/27/09 Miro Spoenemann (msp@informatik.uni-kiel.de)
#
# restructured: 03/17/10 Christian Schneider (chsch@...)
#   the makefile is completely generic concerning platform specific configurations 
#   (platform specific params are stored in target specific variables).
#   
#   YOU HAVE TO PROVIDE the path to the OGDF sources (variable OGDF, see below), only.
#
#   Call 'make [win32|win64|osx32|osx64|linux32|linux64|solaris]' on
#     calendar.rtsys (win32,win64,osx32,osx64)
#     epoch.rtsys (linux32)
#     hudson.rtsys (linux64)
#     sauron.informatik.uni-kiel.de (solaris)

OGDF = /Users/chandler/ogdf-lib
#OGDF = ~/OGDF/ogdf

# name /location of the lib to be build
LIB = ogdf
LIB_DIR = lib

SWIG = swig
SWIGFLAGS = -c++ -java -I$(OGDF) -ignoremissing

COPTS = -O2 -I. -I$(OGDF) -fmessage-length=0

OGDFsrc = ${shell find $(OGDF)/src -iname '*.cpp' -print}

#generating SWIG wrapper
ogdf_wrap.cpp: ogdf.i
	mkdir -p src/net/ogdf/lib
	$(SWIG) $(SWIGFLAGS) -package net.ogdf.lib -outdir src/net/ogdf/lib -o ogdf_wrap.cpp ogdf.i

#compiling SWIG wrapper ( ./ogdf_wrap.cpp)
$(LIB_DIR)/%/ogdf_wrap.o : ogdf_wrap.cpp
	mkdir -p $(@D)
	$(CC) $(COPTS) $(JAVA_INC) -c -o $@ $<


.SECONDEXPANSION:

#compiling OGDF sources
$(OGDF)/%.o: $$(subst .o,.cpp, $$(subst solaris,src,$$(subst linux64,src,$$(subst linux32,src,$$(subst osx64,src,$$(subst osx32,src,$$(subst win64,src,$$(subst win32,src,$$@))))))))
	mkdir -p $(@D)
	$(CC) $(COPTS) $(JAVA_INC) -c -o $@ $<
	
#linking all the stuff
$(LIB_DIR)/%: $$(subst .cpp,.o, $$(subst src,$$(*D),$(OGDFsrc))) $$(@D)/ogdf_wrap.o
	mkdir -p $(@D)
	$(CC) $(COPTS) $(JAVA_INC) $(LOPTS) $(JAVA_LIB) -o $@ $^


# run this target on calendar (it's a cross compiler)
win32: CC = /opt/mingw-w32/bin/i686-w64-mingw32-g++ -m32
win32: JAVA_INC = -I/System/Library/Frameworks/JavaVM.framework/Headers
win32: LOPTS = -shared -static-libgcc -static-libstdc++
win32: JAVA_LIB =
win32: $(LIB_DIR)/$$@/$(LIB).dll

# run this target on calendar (it's a cross compiler)
win64: CC = /opt/mingw-w64/bin/x86_64-w64-mingw32-g++ -m64
win64: JAVA_INC = -I/System/Library/Frameworks/JavaVM.framework/Headers
win64: LOPTS = -shared -static-libgcc -static-libstdc++
win64: JAVA_LIB =
win64: $(LIB_DIR)/$$@/$(LIB).dll

# run this target on calendar
osx32: CC = g++ -m32
osx32: JAVA_INC = -I/System/Library/Frameworks/JavaVM.framework/Headers
osx32: LOPTS = -dynamiclib -static-libgcc -static-libstdc++ -mmacosx-version-min=10.5
osx32: JAVA_LIB = -framework JavaVM
osx32: $(LIB_DIR)/$$@/lib$(LIB).jnilib $?

# run this target on calendar
osx64: CC = g++ -m64
osx64: JAVA_INC = -I/System/Library/Frameworks/JavaVM.framework/Headers
osx64: LOPTS = -dynamiclib -static-libgcc -static-libstdc++ -mmacosx-version-min=10.5
osx64: JAVA_LIB = -framework JavaVM
osx64: $(LIB_DIR)/$$@/lib$(LIB).jnilib $?

# run this target on epoch
linux32: CC = g++ -fPIC -m32
linux32: JAVA_INC = -I/usr/lib/jvm/java-6-openjdk/include -I/usr/lib/jvm/java-6-openjdk/include/linux
linux32: LOPTS = -shared -static-libgcc
linux32: JAVA_LIB =
linux32: $(LIB_DIR)/$$@/lib$(LIB).so $?

# run this target on hudson
linux64: CC = g++ -fPIC -m64
linux64: JAVA_INC = -I/usr/lib/jvm/java-6-sun/include -I/usr/lib/jvm/java-6-sun/include/linux
linux64: LOPTS = -shared -static-libgcc
linux64: JAVA_LIB =
linux64: $(LIB_DIR)/$$@/lib$(LIB).so $?

# run this target on sauron
solaris: CC = g++ -fPIC
solaris: JAVA_INC = -I/usr/java/include -I/usr/java/include/solaris
solaris: LOPTS = -shared -mimpure-text
solaris: JAVA_LIB = 
solaris: $(LIB_DIR)/$$@/lib$(LIB).so $?
