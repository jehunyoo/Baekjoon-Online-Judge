// 영역 구하기
#include <iostream>
#include <cstdio>
#include <stack>
#include <utility>
#include <algorithm>
#define MAXSIZE 100
#define MAXCOUNT 5000

typedef std::pair<int,int> Node;
int paper[MAXSIZE][MAXSIZE];

int main(void)
{
    int M, N, K, x1, y1, x2, y2;

    scanf("%d %d %d", &M, &N, &K);

    while(K--) {
        scanf("%d %d %d %d", &y1, &x1, &y2, &x2);
        for(int x=x1; x<x2; x++)
            for(int y=y1; y<y2; y++)
                paper[x][y] = 1;
    }

    int count = 0, area = 0;
    int areas[MAXCOUNT] = {0};
    int visited[MAXSIZE][MAXSIZE] = {0};
    Node node;
    std::stack<Node> st;

    for(int x=0; x<M; x++) {
        for(int y=0; y<N; y++) {
            if(paper[x][y] == 0 && visited[x][y] != 1) { // Note: paper[x][y] == 0
                area = 0;
                st.push(std::make_pair(x, y));
                while(!st.empty()) {
                    node = st.top(); st.pop();
                    if(visited[node.first][node.second] == 0) {
                        visited[node.first][node.second] = 1;
                        area++;

                        if(node.first + 1 < M && paper[node.first + 1][node.second] == 0 && visited[node.first + 1][node.second] != 1)
                            st.push(std::make_pair(node.first + 1, node.second));
                        if(node.second + 1 < N && paper[node.first][node.second + 1] == 0 && visited[node.first][node.second + 1] != 1)
                            st.push(std::make_pair(node.first, node.second + 1));
                        if(0 <= node.first - 1 && paper[node.first - 1][node.second] == 0 && visited[node.first - 1][node.second] != 1)
                            st.push(std::make_pair(node.first - 1, node.second));
                        if(0 <= node.second - 1 && paper[node.first][node.second - 1] == 0 && visited[node.first][node.second - 1] != 1)
                            st.push(std::make_pair(node.first, node.second - 1));
                    }
                }
                areas[count++] = area;
            }
        }
    }

    std::sort(areas, areas+count);
    printf("%d\n", count);
    for(int i=0; i<count; i++)
        printf("%d ", areas[i]);
    printf("\n");

    return 0;
}