# KMP Dreams of Sushi: The Game

UNC comp 401 - Introduction to Object Oriented Programming final project

This code contains a complete, functional sushi restaurant game. You should be able to run and play the game starting from the class ```sushigame.game.SushiGame```. You should see an interface that includes a scoreboard, a visualization of a sushi belt, an interface for creating a few specific types of sushi on specific plate colors placed nearest to a specific position, a rotate button, and a message area where error messages are displayed. 

In this game, there are 20 positions on the belt, 5 customers sitting evenly spread out at different positions, and 4 opponent sushi chefs. Each chef is allowed to (but not required to) make and place one plate of sushi per rotation. Each chef starts with $100 in their account. When a plate of sushi is made a placed on the belt, the cost of the ingredients is subtracted from the chef's account balance. When the rotate button is pressed, any spoiled sushi is removed from the belt. Then each customer evaluates the plate in front of them (if any) and may or may not decide to consume that plate. If they do consume the plate, the chef who made the plate is credited with the price of the plate.
