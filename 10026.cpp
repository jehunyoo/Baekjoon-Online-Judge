// 적록 색약
#include <iostream>
#include <cstdio>
#include <stack>
#include <utility>
#define MAXSIZE 100
// #define R 0
// #define G 1
// #define B 2

typedef std::pair<int,int> Node;

int N;
int grid[MAXSIZE][MAXSIZE];
int dfs();

int main(void)
{
    char str[MAXSIZE + 1];

    scanf("%d", &N);
    for(int i=0; i<N; i++) {
        scanf("%s", str);
        for(int j=0; j<N; j++) {
            grid[i][j] = str[j];
        }
    }

    int answer1 = dfs();
    
    for(int i=0; i<N; i++) {
        for(int j=0; j<N; j++) {
            if(grid[i][j] == 'R')
                grid[i][j] = 'G';
        }
    }

    int answer2 = dfs();

    printf("%d %d\n", answer1, answer2);

    return 0;
}

int dfs()
{
    int color, count = 0;
    int visited[MAXSIZE][MAXSIZE] = {0};
    std::stack<Node> st;
    Node node;

    for(int i=0; i<N; i++) {
        for(int j=0; j<N; j++) {
            if(!visited[i][j]) {
                st.push(std::make_pair(i, j));
                color = grid[i][j];
                count++;

                while(!st.empty()) {
                    node = st.top(); st.pop();
                    int x = node.first, y = node.second;
                    if(!visited[x][y]) {
                        visited[x][y] = 1;
                        if(x + 1 < N && !visited[x + 1][y] && grid[x + 1][y] == color)
                            st.push(std::make_pair(x + 1, y));
                        if(y + 1 < N && !visited[x][y + 1] && grid[x][y + 1] == color)
                            st.push(std::make_pair(x, y + 1));
                        if(0 <= x - 1 && !visited[x - 1][y] && grid[x - 1][y] == color)
                            st.push(std::make_pair(x - 1, y));
                        if(0 <= y - 1 && !visited[x][y - 1] && grid[x][y - 1] == color)
                            st.push(std::make_pair(x, y - 1));
                    }
                }
            }
        }
    }

    return count;
}