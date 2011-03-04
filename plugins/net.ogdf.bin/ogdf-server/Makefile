#------------------------------------------------------------------------------
# Makefile for compilation of the OGDF-server for the concrete application of
# building an OGDF-KIML bridge.
#
# Initial version: 12/22/10 Martin Riess (mri@informatik.uni-kiel.de)
#   
#   YOU HAVE TO PROVIDE the path to the OGDF library (variable OGDF, see below), only.
#
#   Call 'make [osx32|osx64|linux32|linux64|solaris]'
#

# mac: /Users/rtsys/workspace/mri/ogdf/OGDF
OGDF = /home/mri/shared/OGDF_SVN/SVNOGDF/OGDF

BIN = ogdf-server
SRC_DIR = src
BIN_DIR = bin
OBJ_DIR = $(BIN_DIR)

COPTS = 
INCS = -I$(OGDF) -Iinclude

SRCS = $(wildcard $(SRC_DIR)/*.cpp)
OBJS = $(addprefix $(OBJ_DIR)/, $(patsubst %.cpp, %.o, $(notdir $(SRCS))))

#OGDFobj = ${shell find $(OGDF)/_release -iname '*.o' -print}

default:
	@echo "Use one of the targets: osx32, osx64, linux32, linux64, solaris"

# compiling all ogdf-server sources
$(OBJ_DIR)/%.o: $(SRC_DIR)/%.cpp 
	mkdir -p $(@D)
	$(CC) $(COPTS) $(INCS) -c -o $@ $<  

.SECONDEXPANSION:
	
#linking all the stuff
$(BIN_DIR)/%: $(OBJS)
	mkdir -p $(@D)
	$(CC) $(LOPTS) $(LIBS) -o $@ $(OBJS) $(OGDF)/_release/libOGDF.a

# run this target on a MacOS machine
osx32: CC = g++ -m32
osx32: LOPTS = -static-libgcc -static-libstdc++ -mmacosx-version-min=10.5
osx32: LIBS =
osx32: $(BIN_DIR)/$$@/$(BIN) $?

# run this target on a MacOS machine
osx64: CC = g++ -m64
osx64: LOPTS = -static-libgcc -static-libstdc++ -mmacosx-version-min=10.5
osx64: LIBS =
osx64: $(BIN_DIR)/$$@/$(BIN) $?

# run this target on a Linux machine
linux32: CC = g++ -m32
linux32: LOPTS =
linux32: LIBS = -lpthread
linux32: $(BIN_DIR)/$$@/$(BIN) $?

# run this target on a Linux machine
linux64: CC = g++ -m64
linux64: LOPTS =
linux64: LIBS = -lpthread
linux64: $(BIN_DIR)/$$@/$(BIN) $?

# run this target on a SunOS machine
solaris: CC = g++ -fPIC
solaris: LOPTS = -shared -mimpure-text
solaris: LIBS =
solaris: $(LIB_DIR)/$$@/lib$(LIB).so $?

clean:
	rm -rf $(BIN_DIR)

