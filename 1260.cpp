// DFS와 BFS
#include <iostream>
#include <cstdio>
#include <stack>
#include <queue>
#define MAXSIZE 1001

int graph[MAXSIZE][MAXSIZE];

int main(void)
{
    int N, M, V, node1, node2;
    scanf("%d %d %d", &N, &M, &V);

    while(M--) {
        scanf("%d %d", &node1, &node2);
        graph[node1][node2] = 1;
        graph[node2][node1] = 1;
    }

    int node;

    // DFS
    int visited_dfs[MAXSIZE] = {0};
    std::stack<int> st;
    st.push(V);

    while(!st.empty()) {
        node = st.top(); st.pop();
        if(!visited_dfs[node]) {
            visited_dfs[node] = 1;
            printf("%d ", node);
            for(int i=N; i>=1; i--)
                if(graph[node][i] && !visited_dfs[i])
                    st.push(i);
        }
    }
    
    printf("\n");

    // BFS
    int visited_bfs[MAXSIZE] = {0};
    std::queue<int> q;
    q.push(V);

    while(!q.empty()) {
        node = q.front(); q.pop();
        if(!visited_bfs[node]) {
            visited_bfs[node] = 1;
            printf("%d ", node);
            for(int i=1; i<=N; i++)
                if(graph[node][i] && !visited_bfs[i])
                    q.push(i);
        }
    }

    printf("\n");

    return 0;
}