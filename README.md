# Carol-Scout
We should help scout get to the destination point, we will come across dangers like high ice cube hills and water(where we cannot take or put any ice cube) we are allowed to step +- 1 step up or downwards. s means step, r and l change directions we are facing, n and p puts and takes blocks, we cannot carry more than 10 blocks. There is MiniJava class in order to be able to print the playground. Latter was given by the university KIU.


playground.length is the width of the playing field and playground[0].length is the height. The coordinate system still has its origin at the bottom left and playground[x][y] is the height of the field at position (x, y). The height is a value from -1 to 9. -1 is water that is a block of ice deep. The height is the number of ice blocks above the water, and the number of ice blocks on a field is equal to the height plus one. Carol can move within this playing field and carry around 0 to 10 blocks of ice. Carol's viewing direction can be 0 (pos. X / right), 1 (pos. Y / up), 2 (neg. X / left) and 3 (neg. Y / down). The following instructions can be used to navigate the field:

'r' Carol turns right from her own point of view.
'l' Carol turns left from her own point of view.
's' Carol takes a step in the current direction of gaze. For this, the absolute height difference must be less than or equal to one.
'p' Carol places a block of ice on the field in the line of sight. To do this, she must carry at least one block of ice and the field in front of her must not have reached the maximum height (9). If Carol is in the water, she cannot place blocks of ice either.
'n' Carol takes a block of ice from the field in the direction of view. To do this, she must be able to pick up at least one block of ice and the space in front of her must not be water (-1). If Carol is in the water, she cannot take any blocks of ice either.
Of course, Carol cannot move outside the field, take or place blocks there.

Analyze Carol's instructions
First, let's write a few helper methods to better understand Carol's current position on the field. We want to analyze a sequence of Penguin Carol instructions that is stored in an array. It is particularly interesting whether the last instructions stored in an array make sense. So that we can later use the methods efficiently for the pathfinder program, we enter in the parameter filled with two methods how many instructions the array is currently filled with (the point here is to be able to use an array over and over again). None of the following three methods may change the content of the array.
