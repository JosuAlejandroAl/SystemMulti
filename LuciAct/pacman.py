from mesa import Agent, Model
from mesa.space import Grid as mGrid
from mesa.time import RandomActivation
from mesa.visualization.modules import CanvasGrid
from mesa.visualization.ModularVisualization import ModularServer
from pathfinding.core.diagonal_movement import DiagonalMovement
from pathfinding.core.grid import Grid as pGrid
from pathfinding.finder.a_star import AStarFinder


class Ghost(Agent):
    def __init__(self, model, pos, cont):
        super().__init__(model.next_id(), model)
        self.cont = cont
        self.pos = pos
        self.matrix = [
            [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
            [0,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,0],
            [0,1,0,1,0,0,0,1,1,1,0,1,0,1,0,1,0],
            [0,1,1,1,0,1,0,0,0,0,0,1,0,1,1,1,0],
            [0,1,0,0,0,1,1,1,1,1,1,1,0,0,0,1,0],
            [0,1,0,1,0,1,0,0,0,0,0,1,1,1,0,1,0],
            [0,1,1,1,0,1,0,1,1,1,0,1,0,1,0,1,0],
            [0,1,0,1,0,1,0,1,1,1,0,1,0,1,0,1,0],
            [0,1,0,1,1,1,0,0,1,0,0,1,0,1,1,1,0],
            [0,1,0,0,0,1,1,1,1,1,1,1,0,0,0,1,0],
            [0,1,1,1,0,1,0,0,0,0,0,1,0,1,1,1,0],
            [0,1,0,1,0,1,0,1,1,1,0,0,0,1,0,1,0],
            [0,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,0],
            [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
        ]
    def step(self):
        red = pGrid(matrix=self.matrix)
        start = red.node(8, 6)
        end = red.node(2, 1)
        finder = AStarFinder(diagonal_movement=DiagonalMovement.never)
        path = finder.find_path(start, end, red)
        print(path[0][self.cont])
        next_move = path[0][self.cont]
        self.model.grid.move_agent(self, next_move)
        self.cont+=1

class WallBlock(Agent):
    def __init__(self, model, pos):
        super().__init__(model.next_id(), model)
        self.pos = pos

class Maze(Model):
    def __init__(self):
        super().__init__()
        self.schedule = RandomActivation(self)
        self.grid = mGrid(17, 14, torus=False)
        matrix = [
            [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
            [0,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,0],
            [0,1,0,1,0,0,0,1,1,1,0,1,0,1,0,1,0],
            [0,1,1,1,0,1,0,0,0,0,0,1,0,1,1,1,0],
            [0,1,0,0,0,1,1,1,1,1,1,1,0,0,0,1,0],
            [0,1,0,1,0,1,0,0,0,0,0,1,1,1,0,1,0],
            [0,1,1,1,0,1,0,1,1,1,0,1,0,1,0,1,0],
            [0,1,0,1,0,1,0,1,1,1,0,1,0,1,0,1,0],
            [0,1,0,1,1,1,0,0,1,0,0,1,0,1,1,1,0],
            [0,1,0,0,0,1,1,1,1,1,1,1,0,0,0,1,0],
            [0,1,1,1,0,1,0,0,0,0,0,1,0,1,1,1,0],
            [0,1,0,1,0,1,0,1,1,1,0,0,0,1,0,1,0],
            [0,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,0],
            [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
        ]

        ghost = Ghost(self, (8, 6),0)
        self.grid.place_agent(ghost, ghost.pos)
        self.schedule.add(ghost)

        for _,x,y in self.grid.coord_iter():
            if matrix[y][x] == 0:
                print(f"block at ({x},{y})")
                wall = WallBlock(self, (x,y))
                self.grid.place_agent(wall, wall.pos)
                self.schedule.add(wall)
        
    def step(self):
        self.schedule.step()

def agent_portrayal(agent):
    if (type(agent)==Ghost):
        return {"Shape": "ghost.png", "Layer": 0}
    else:
        if(type(agent)==WallBlock):
            return {"Shape": "rect", "w": 1, "h": 1, "Filled": "true", "Color": "Gray", "Layer": 0}
        

grid = CanvasGrid(agent_portrayal, 17, 14, 450, 450)

server = ModularServer(Maze, [grid], "PacMan", {})
server.port = 8522
server.launch()