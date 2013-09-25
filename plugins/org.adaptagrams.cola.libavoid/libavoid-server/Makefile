LIBAVOID = /home/horn/adaptagrams/cola

BIN = libavoid-server
SRC_DIR = src
BIN_DIR = bin
OBJ_DIR = $(BIN_DIR)

COPTS = 
INCS = -I$(LIBAVOID) -Iinclude

SRCS = $(wildcard $(SRC_DIR)/*.cpp)
OBJS = $(addprefix $(OBJ_DIR)/, $(patsubst %.cpp, %.o, $(notdir $(SRCS))))


default:
	@echo "Use one of the targets: osx32, osx64, linux32, linux64, solaris"


# compiling all libavoid-server sources
$(OBJ_DIR)/%.o: $(SRC_DIR)/%.cpp 
	mkdir -p $(@D)
	$(CC) $(COPTS) $(INCS) -c -o $@ $<  

.SECONDEXPANSION:
	
#linking all the stuff
$(BIN_DIR)/%: $(OBJS)
	mkdir -p $(@D)
	$(CC) $(LOPTS) $(LIBS) -o $@ $(OBJS) $(LIBAVOID)/libavoid/.libs/libavoid.a

# run this target on a Linux machine
linux32: CC = g++ -m32
linux32: LOPTS =
linux32: LIBS = -lpthread
linux32: $(BIN_DIR)/$$@/$(BIN) $?


clean: 
	rm -rf $(BIN_DIR)


