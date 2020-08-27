// 안전 영역
#include <iostream>
#include <cstdio>
#include <stack>
#include <utility>
#define MAX(alpha, beta) alpha > beta ? alpha : beta
#define MIN(alpha, beta) alpha > beta ? beta : alpha
#define MAXSIZE 100

typedef std::pair<int, int> Node;

int answer = 1;
/*
rain == low: answer >= 1
rain == high: answer == 0
rain == low - 1: answer == 1
*/
int height[MAXSIZE][MAXSIZE];

int main(void)
{
    int N;
    int low = 100, high = 1;

    scanf("%d", &N);
    for(int i=0; i<N; i++)
        for(int j=0; j<N; j++) {
            scanf("%d", &height[i][j]);
            high = MAX(high, height[i][j]);
            low = MIN(low, height[i][j]);
        }

    Node node;
    std::stack<Node> st;

    for(int rain=low; rain<high; rain++) {
        int count = 0;
        int visited[MAXSIZE][MAXSIZE] = {0};
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(!visited[i][j] && height[i][j] > rain) {
                    st.push(std::make_pair(i, j));
                    count++;

                    while(!st.empty()) {
                        node = st.top(); st.pop();
                        int x = node.first, y = node.second;
                        if(!visited[x][y]) {
                            visited[x][y] = 1;
                            if(x + 1 < N && !visited[x + 1][y] && height[x + 1][y] > rain)
                                st.push(std::make_pair(x + 1, y));
                            if(y + 1 < N && !visited[x][y + 1] && height[x][y + 1] > rain)
                                st.push(std::make_pair(x, y + 1));
                            if(0 <= x - 1 && !visited[x - 1][y] && height[x - 1][y] > rain)
                                st.push(std::make_pair(x - 1, y));
                            if(0 <= y - 1 && !visited[x][y - 1] && height[x][y - 1] > rain)
                                st.push(std::make_pair(x, y - 1));
                        }
                    }
                }
            }
        }
        answer = MAX(answer, count);
    }

    printf("%d\n", answer);

    return 0;
}