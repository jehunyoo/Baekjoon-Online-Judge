// 인구이동
#include <iostream>
#include <cstdio>
#include <stack>
#include <utility>
#include <cmath>
#define MAXSIZE 50

typedef std::pair<int,int> Node;
int Answer = 0;
int A[MAXSIZE][MAXSIZE];

int main(void)
{
    int N, L, R;
    scanf("%d %d %d", &N, &L, &R);

    for(int r=0; r<N; r++)
        for(int c=0; c<N; c++)
            scanf("%d", &A[r][c]);

    int move = 0, count = 0, population = 0, diff, uni=0;
    bool moved = false;
    Node node;
    Node united[MAXSIZE * MAXSIZE];
    std::stack<Node> st;

    do {
        int visited[MAXSIZE][MAXSIZE] = {0};
        for(int r=0; r<N; r++) {
            for(int c=0; c<N; c++) {
                if(!visited[r][c]) {
                    count = 0;
                    population = 0;
                    uni = 0;
                    st.push(std::make_pair(r, c));

                    while(!st.empty()) {
                        node = st.top(); st.pop();
                        int i = node.first, j = node.second;

                        if(!visited[i][j]) {
                            visited[i][j] = 1;
                            count++;
                            population += A[i][j];
                            united[uni++] = std::make_pair(i, j);

                            if(i + 1 < N && !visited[i+1][j]) {
                                diff = std::abs(A[i][j] - A[i+1][j]);
                                if(L <= diff && diff <= R) {
                                    st.push(std::make_pair(i+1, j));
                                    moved = true;
                                }
                            }
                            if(j + 1 < N && !visited[i][j+1]) {
                                diff = std::abs(A[i][j] - A[i][j+1]);
                                if(L <= diff && diff <= R) {
                                    st.push(std::make_pair(i, j+1));
                                    moved = true;
                                }
                            }
                            if(0 <= i - 1 && !visited[i-1][j]) {
                                diff = std::abs(A[i][j] - A[i-1][j]);
                                if(L <= diff && diff <= R) {
                                    st.push(std::make_pair(i-1, j));
                                    moved = true;
                                }
                            }
                            if(0 <= j - 1 && !visited[i][j-1]) {
                                diff = std::abs(A[i][j] - A[i][j-1]);
                                if(L <= diff && diff <= R) {
                                    st.push(std::make_pair(i, j-1));
                                    moved = true;
                                }
                            }
                        }
                    }
                    for(int i=0; i<uni; i++) {
                        node = united[i];
                        A[node.first][node.second] = (int)(population / count);
                    }
                }
            }
        }

        if(moved) {
            move++;
            moved = false;
        }
        else
            break;
        
    } while(true);

    printf("%d\n", move);

    return 0;
}