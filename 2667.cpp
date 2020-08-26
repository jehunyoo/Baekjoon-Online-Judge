// 단지 번호 붙이기
#include <iostream>
#include <cstdio>
#include <stack>
#include <utility>
#include <algorithm>
#define MAXSIZE 25
#define MAXTOWN 577

typedef std::pair<int, int> Node;

int map[MAXSIZE][MAXSIZE];

int main(void)
{
    int N;
    char str[MAXSIZE + 1];
    scanf("%d", &N);

    for(int i=0; i<N; i++) {
        scanf("%s", str);
        for(int j=0; j<N; j++)
            map[i][j] = str[j] - '0';
    }

    int size = 0, count = 0;
    int town[MAXTOWN] = {0};
    int visited[MAXSIZE][MAXSIZE] = {0};
    Node node;
    std::stack<Node> st;

    for(int i=0; i<N; i++) {
        for(int j=0; j<N; j++) {
            if(map[i][j] == 1 && visited[i][j] != 1) {

                size = 0;
                st.push(std::make_pair(i, j));

                while(!st.empty()) {
                    node = st.top(); st.pop();
                    if (visited[node.first][node.second] == 0) {
                        visited[node.first][node.second] = 1;
                        size++;
                    }

                    if(node.first + 1 < N && map[node.first+1][node.second] == 1 && visited[node.first+1][node.second] != 1)
                        st.push(std::make_pair(node.first + 1, node.second));
                    if(node.second + 1 < N && map[node.first][node.second+1] == 1 && visited[node.first][node.second+1] != 1)
                        st.push(std::make_pair(node.first, node.second + 1));
                    if(0 <= node.first - 1 && map[node.first-1][node.second] == 1 && visited[node.first-1][node.second] != 1)
                        st.push(std::make_pair(node.first - 1, node.second));
                    if(0 <= node.second - 1 && map[node.first][node.second-1] == 1 && visited[node.first][node.second-1] != 1)
                        st.push(std::make_pair(node.first, node.second - 1));
                }
                town[count++] = size;
            }
        }
    }

    std::sort(town, town+count);
    printf("%d\n", count);
    for(int i=0; i<count; i++)
        printf("%d\n", town[i]);

    return 0;
}