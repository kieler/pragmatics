#------------------------------------------------------------------------------
# Makefile for compilation of OGDF as shared library with JNI wrapper for use
# in Java applications. This requires SWIG to be installed:
#     http://www.swig.org/
#
# Initial version: 9/27/09 Miro Spoenemann (msp@informatik.uni-kiel.de)
#

# path to the OGDF library, release version must be compiled
OGDF = null
# path to the Java header files
JAVAH = null
# header files to include in the JNI wrapper
HEADERS = $(OGDF)/ogdf/basic/memory.h $(OGDF)/ogdf/basic/geometry.h $(OGDF)/ogdf/basic/List.h $(OGDF)/ogdf/basic/Graph_d.h $(OGDF)/ogdf/basic/Graph.h $(OGDF)/ogdf/basic/GraphAttributes.h $(OGDF)/ogdf/module/LayoutModule.h $(OGDF)/ogdf/layered/SugiyamaLayout.h

OS = $(shell uname -s)
OSTYPE = $(shell echo $$OSTYPE)
CC = g++
SWIG = swig

# check user variables
ifeq ($(OGDF),null)
  $(error Variable OGDF was not defined)
endif

ifeq ($(JAVAH),null)
  $(error Variable JAVAH was not defined)
endif

ifeq ($(OS),Darwin)
  LIB = libogdf.dylib
  SFLAGS = -dynamiclib
else ifeq ($(OS),Linux)
  LIB = libogdf.so
  SFLAGS = -shared
else ifeq ($(OS),SunOS)
  LIB = libogdf.so
  SFLAGS = -shared
else ifeq ($(OSTYPE),cygwin)
  LIB = ogdf.dll
  SFLAGS = -shared
endif

# all target builds the wrapper code and the shared library
all: wrap lib

# wrap target builds the wrapper code using SWIG
wrap: ogdf_wrap.cpp

ogdf_wrap.cpp: ogdf_header.i $(HEADERS)
	cat ogdf_header.i $(HEADERS) > ogdf.i
	mkdir -p src/net/ogdf/lib
	$(SWIG) -c++ -java -package net.ogdf.lib -outdir src/net/ogdf/lib -o ogdf_wrap.cpp ogdf.i

# lib target builds the shared library for use in Java
lib: lib/$(LIB)

lib/$(LIB): $(OGDF)/_release/libOGDF.a ogdf_wrap.cpp
	$(CC) -fPIC -c -I $(OGDF) -I $(JAVAH) ogdf_wrap.cpp
	mkdir -p lib
	$(CC) $(SFLAGS) -o lib/$(LIB) $(OGDF)/_release/libOGDF.a ogdf_wrap.o

# clean target removes the wrapper code and the shared library
clean:
	rm -f ogdf_wrap.cpp lib/$(LIB) src/net/ogdf/lib/*.java

