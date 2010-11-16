#------------------------------------------------------------------------------
# Makefile for compilation of OGDF as shared library with JNI wrapper for use
# in Java applications.
#
# Initial version: 7/7/10 Martin Riess (mri@informatik.uni-kiel.de)
#   
#   YOU HAVE TO PROVIDE the path to the OGDF sources (variable OGDF, see below), only.
#
#   Call 'make [win32|win64|osx32|osx64|linux32|linux64|solaris]'
#

OGDF = /home/msp/lab/ogdf.v2010.10

# name /location of the lib to be build
LIB = ogdf
LIB_DIR = lib

COPTS = -I. -I$(OGDF) -fmessage-length=0

OGDFobj = ${shell find $(OGDF)/_release -iname '*.o' -print}

default:
	@echo "Use one of the targets: win32, win64, osx32, osx64, linux32, linux64, solaris"

#compiling wrapper ( ./ogdf_wrap.cpp)
$(LIB_DIR)/%/ogdf_wrap.o : ogdf_wrap.cpp
	mkdir -p $(@D)
	$(CC) $(COPTS) $(JAVA_INC) -c -o $@ $<


.SECONDEXPANSION:
	
#linking all the stuff
#$(OGDFobj)
$(LIB_DIR)/%: $$(@D)/ogdf_wrap.o
	mkdir -p $(@D)
	$(CC) $(COPTS) $(JAVA_INC) $(LOPTS) $(JAVA_LIB) -o $@ $(@D)/ogdf_wrap.o $(OGDF)/_release/libOGDF.a $(LIBS)


# run this target on a windows machine with mingw (the gcc 4.5 version)
# -Wl,--kill-at -Wl,--add-stdcall-alias
win32: CC = g++ -fmessage-length=0 -msse3 -I. -DOGDF_THROW_WITH_INFO -O1 -enable-auto-import -Wl,--add-stdcall-alias
win32: JAVA_INC = -IC:/Programme/Java/jdk1.6.0_21/include -IC:/Programme/Java/jdk1.6.0_21/include/win32
win32: LOPTS = -shared
win32: LIBS = -lpsapi
win32: JAVA_LIB =
win32: $(LIB_DIR)/$$@/$(LIB).dll

# run this target on a MacOS machine with Win64 cross-compiler
win64: CC = /opt/mingw-w64/bin/x86_64-w64-mingw32-g++ -m64
win64: JAVA_INC = -I/System/Library/Frameworks/JavaVM.framework/Headers
win64: LOPTS = -shared -static-libgcc -static-libstdc++
win64: LIBS =
win64: JAVA_LIB =
win64: $(LIB_DIR)/$$@/$(LIB).dll

# run this target on a MacOS machine
osx32: CC = g++ -fPIC -m32
osx32: JAVA_INC = -I/System/Library/Frameworks/JavaVM.framework/Headers
osx32: LOPTS = -dynamiclib -static-libgcc -static-libstdc++ -mmacosx-version-min=10.5
osx32: LIBS =
osx32: JAVA_LIB = -framework JavaVM
osx32: $(LIB_DIR)/$$@/lib$(LIB).jnilib $?

# run this target on a MacOS machine
osx64: CC = g++ -fPIC -m64
osx64: JAVA_INC = -I/System/Library/Frameworks/JavaVM.framework/Headers
osx64: LOPTS = -dynamiclib -static-libgcc -static-libstdc++ -mmacosx-version-min=10.5
osx64: LIBS =
osx64: JAVA_LIB = -framework JavaVM
osx64: $(LIB_DIR)/$$@/lib$(LIB).jnilib $?

# run this target on a Linux machine
linux32: CC = g++ -fPIC -m32
linux32: JAVA_INC = -I/home/java/jdk1.6/include -I/home/java/jdk1.6/include/linux
linux32: LOPTS = -shared -static-libgcc
linux32: LIBS =
linux32: JAVA_LIB =
linux32: $(LIB_DIR)/$$@/lib$(LIB).so $?

# run this target on a Linux machine
linux64: CC = g++ -fPIC -m64
linux64: JAVA_INC = -I/home/java/jdk64_1.6/include -I/home/java/jdk64_1.6/include/linux
linux64: LOPTS = -shared -static-libgcc
linux64: LIBS =
linux64: JAVA_LIB =
linux64: $(LIB_DIR)/$$@/lib$(LIB).so $?

# run this target on a SunOS machine
solaris: CC = g++ -fPIC
solaris: JAVA_INC = -I/usr/java/include -I/usr/java/include/solaris
solaris: LOPTS = -shared -mimpure-text
solaris: LIBS =
solaris: JAVA_LIB = 
solaris: $(LIB_DIR)/$$@/lib$(LIB).so $?


clean:
	rm -rf $(LIB_DIR)

