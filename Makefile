NAME=GameOfLife.jar

SRC = \
./src/Coords.java \
./src/Form.java \
./src/Main.java \
./src/MainKeyListener.java \
./src/MainMouseListener.java \
./src/Mouse.java \
./src/MouseState.java \
./src/World.java \

CLASS = $(notdir $(SRC:.java=.class))
CC= javac -d .
AR= jar
AR_FLAGS = cfe
MAIN_CLASS = Main

all: $(NAME) clean

deb:
	@echo "CLASS:  " $(CLASS)

run: all
	java -jar $(NAME)


$(NAME): $(CLASS)
	$(AR) $(AR_FLAGS) $(NAME) $(MAIN_CLASS) $(CLASS)

$(CLASS): $(SRC)
	$(CC) $(SRC)

clean:
	rm $(CLASS)

fclean:
	rm $(NAME)

re: fclean all

.PHONY: deb run clean fclean all re

