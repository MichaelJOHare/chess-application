# chess-application
Refactor of my chess-game to use the MVC pattern to decouple game logic from the GUI allowing for easier testing and possible integration of AI opponents.  May work on allowing players to play other players in the future.

White pieces always starts first, click a square to select a piece then click the square you want to move it to.  Legal moves will have their squares highlighted in yellow.  If you choose the wrong piece by accident you can move it to a legal square then use the Undo button to choose a different piece or move to an illegal square and it will prompt you to choose a new piece right away.
