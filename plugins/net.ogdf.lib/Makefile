#------------------------------------------------------------------------------
# Makefile for compilation of OGDF as shared library with JNI wrapper for use
# in Java applications. This requires SWIG to be installed:
#     http://www.swig.org/
#
# Initial version: 9/27/09 Miro Spoenemann (msp@informatik.uni-kiel.de)
#

OS = $(shell uname -s)
OSTYPE = $(shell echo $$OSTYPE)
CC = g++ -m32
SWIG = swig
SWIGFLAGS = -c++ -java -I$(OGDF) -ignoremissing

OGDF_OBJS = ${shell find $(OGDF)/_release/src -iname '*.o' -print}

# check user variables
ifeq ($(OGDF),null)
  $(error Variable OGDF was not defined)
endif

ifeq ($(JAVAH),null)
  $(error Variable JAVAH was not defined)
endif

ifeq ($(OS),Darwin)
  LIB = libogdf.jnilib
  LINKFLAGS = -dynamiclib -mmacosx-version-min=10.5
  # path to the Java header files
  JAVAH = /System/Library/Frameworks/JavaVM.framework/Headers
  # path to the OGDF library, release version must be compiled
  OGDF = /Users/chandler/ogdf-lib
else ifeq ($(OS),Linux)
  LIB = libogdf.so
  LINKFLAGS = -shared
  # path to the Java header files
  JAVAH = /usr/lib/jvm/java-6-sun/include -I /usr/lib/jvm/java-6-sun/include/linux
  # path to the OGDF library, release version must be compiled
  OGDF = /home/cvx/ogdf/OGDF
else ifeq ($(OS),SunOS)
  LIB = libogdf.so
  LINKFLAGS = -shared
else ifeq ($(OSTYPE),cygwin)
  LIB = ogdf.dll
  LINKFLAGS = -shared
endif

# all target builds the wrapper code and the shared library
all: wrap lib

# wrap target builds the wrapper code using SWIG
wrap: ogdf_wrap.cpp

ogdf_wrap.cpp: ogdf.i
	mkdir -p src/net/ogdf/lib
	$(SWIG) $(SWIGFLAGS) -package net.ogdf.lib -outdir src/net/ogdf/lib -o ogdf_wrap.cpp ogdf.i

# lib target builds the shared library for use in Java
lib: lib/$(LIB)

lib/$(LIB): $(OGDF)/_release/libOGDF.a ogdf_wrap.cpp
	$(CC) -fPIC -c -I $(OGDF) -I $(JAVAH) ogdf_wrap.cpp
	mkdir -p lib
	$(CC) $(LINKFLAGS) -o lib/$(LIB) $(OGDF_OBJS) ogdf_wrap.o
#$(OGDF)/_release/libOGDF.a

# clean target removes the wrapper code and the shared library
clean:
	rm -f ogdf_wrap.cpp lib/$(LIB) src/net/ogdf/lib/*.java

