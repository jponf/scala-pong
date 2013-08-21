###
#
# Builds scala-pong and creates scala-pong.jar
#
# To run the jar file it is necessary to have the file press_start_2p.ttf in the
# same folder
#
###

COMPILER = scalac
BIN_OUTPUT=./scala-pong.jar

SRC_FILES := $(wildcard src/pong/*.scala)
SRC_FILES += $(wildcard src/helpers/*.scala)
SRC_FILES += $(wildcard src/scene/*.scala)
SRC_FILES += $(wildcard src/scene/input/*.scala)

$(BIN_OUTPUT): $(SRC_FILES)
	@echo -n "Compiling $(BIN_OUTPUT) ... " 
	@$(COMPILER) -d $(BIN_OUTPUT) $(SRC_FILES) 
	@echo "Done"
