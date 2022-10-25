# https://github.com/BrianZhang2018/Pawns-Gaming-Algorithm
# 这答案缺东西, failed on rally healthy OA.

def solution(B):
    pos_x = 0
    pos_y = 0
    
    m=len(B)
    n=len(B[0])
    for i in range(m):
        for j in range(n):
            
            if B[i][j] == 'O':
                pos_x = i
                pos_y = j

    if pos_x-1 < 0:
        return 0
    elif pos_x + 1 >= len(B):
        x = 0
        if B[pos_x-1][pos_y+1] == "X" and pos_y+1<n:
            if pos_x-2>0 and pos_y+2<n and B[pos_x-2][pos_y+2] != "X":
                x+=1
        if B[pos_x-1][pos_y-1] == "X" and pos_x-1>0: 
            x+=1
        return x
    else:
        return 0
    pass


B = ['X....', '.X...', '..O..', '...X.', '.....'] 
B1 = ['X....', '.X...', '..O..', '...X.', '.....']
B2 = ['..X...', '......', '....X.', 'XXXXX.', '..X..X', '....O.']
print(solution(B))
print(solution(B1))
print(solution(B2))