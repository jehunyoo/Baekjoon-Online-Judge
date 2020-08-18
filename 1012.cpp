#include <iostream>
#include <cstdio>
#include <stack>
#define MAX 50

int countWarms(int farm[][MAX], int M, int N);

int main(void)
{
    int T, M, N, K, X, Y;
    scanf("%d", &T);

    int *outputs = (int *)malloc(sizeof(int) * T);
    
    for (int t=0; t<T; t++) {
        int farm[MAX][MAX] = {0};
        scanf("%d %d %d", &M, &N, &K);

        for (int k=0; k<K; k++) {
            scanf("%d %d", &X, &Y);
            farm[X][Y] = 1; // rows -> X & columns -> Y
        }
        
        outputs[t] = countWarms(farm, M, N);
    }

    for (int t=0; t<T; t++)
        printf("%d\n", outputs[t]);
    
    return 0;
}

int countWarms(int farm[][MAX], int M, int N)
{
    int warm = 0;
    int visited[MAX][MAX];
    std::stack<int> st;

    std::fill(&visited[0][0], &visited[0][0] + sizeof(visited) / sizeof(int), 0);

    for (int i=0; i<M; i++) {
        for (int j=0; j<N; j++) {
            if (farm[i][j] == 1 && visited[i][j] == 0) {
                visited[i][j] = 1;
                warm++;
                st.push(M * j + i); // (i, j) -> M * j + i
                while (!st.empty())
                {
                    int x = st.top() % M; // i -> x
                    int y = st.top() / M; // j -> y
                    visited[x][y] = 1;
                    st.pop();
                    if (x + 1 < M && farm[x+1][y] == 1 && visited[x+1][y] == 0)
                        st.push(M * y + x + 1);
                    if (y + 1 < N && farm[x][y+1] == 1 && visited[x][y+1] == 0)
                        st.push(M * (y+1) + x);
                    if (0 <= x - 1 && farm[x-1][y] == 1 && visited[x-1][y] == 0)
                        st.push(M * y + x - 1);
                    if (0 <= y - 1 && farm[x][y-1] == 1 && visited[x][y-1] == 0)
                        st.push(M * (y-1) + x);
                }
            }
        }
    }

    return warm;
}