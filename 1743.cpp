// 음식물 피하기
#include <iostream>
#include <cstdio>
#include <stack>
#include <utility>
#define MAXSIZE 101

int main(void)
{
    int N, M, K, r, c;
    int aisle[MAXSIZE][MAXSIZE] = {0};
    scanf("%d %d %d", &N, &M, &K);

    for(int k=0; k<K; k++) {
        scanf("%d %d", &r, &c);
        aisle[r][c] = 1;
    }

    int biggest = 0, big = 0;
    int visited[MAXSIZE][MAXSIZE] = {0};
    std::pair<int, int> pr;
    std::stack< std::pair<int, int> > st;

    for(int i=1; i<=N; i++) {
        for(int j=1; j<=M; j++) {
            if(aisle[i][j] == 1 && visited[i][j] != 1) {
                big = 0;
                st.push(std::make_pair(i, j));

                while(!st.empty()) {
                    
                    pr = st.top(); st.pop();
                    if(visited[pr.first][pr.second] != 1) {
                        visited[pr.first][pr.second] = 1;
                        big++;
                    }

                    if(pr.first + 1 <= N && aisle[pr.first + 1][pr.second] == 1 && visited[pr.first + 1][pr.second] != 1)
                        st.push(std::make_pair(pr.first + 1, pr.second));
                    if(1 <= pr.first - 1 && aisle[pr.first - 1][pr.second] == 1 && visited[pr.first - 1][pr.second] != 1)
                        st.push(std::make_pair(pr.first - 1, pr.second));
                    if(pr.second + 1 <= M && aisle[pr.first][pr.second + 1] == 1 && visited[pr.first][pr.second + 1] != 1)
                        st.push(std::make_pair(pr.first, pr.second + 1));
                    if(1 <= pr.second - 1 && aisle[pr.first][pr.second - 1] == 1 && visited[pr.first][pr.second - 1] != 1)
                        st.push(std::make_pair(pr.first, pr.second - 1));
                }

                biggest = biggest > big ? biggest : big;
            }
        }
    }

    printf("%d\n", biggest);

    return 0;
}