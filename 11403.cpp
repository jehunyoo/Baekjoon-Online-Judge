// 경로 찾기
#include <iostream>
#include <cstdio>
#include <stack>
#define MAX 100

int mat[MAX][MAX];
int ans[MAX][MAX];

int main(void)
{
    int N, isEdge;
    scanf("%d", &N);

    for(int i=0; i<N; i++) {
        for(int j=0; j<N; j++) {
            scanf("%d", &isEdge);
            mat[i][j] = isEdge;
        }
    }

    int node;
    bool ignoreFirstNode;
    std::stack<int> st;

    for(int i=0; i<N; i++) {
        int visited[MAX] = {0};
        st.push(i);
        ignoreFirstNode = true;
        
        while(!st.empty()) {
            node = st.top(); st.pop();
            if(visited[node] == 0) {
                if(ignoreFirstNode)
                    ignoreFirstNode = false;
                else
                    visited[node] = 1;
                for(int k=0; k<N; k++)
                    if(mat[node][k] == 1 && visited[k] != 1)
                        st.push(k);
            }
        }
        for(int k=0; k<N; k++)
            if(visited[k] == 1)
                ans[i][k] = 1;
}

    for(int i=0; i<N; i++) {
        for(int j=0; j<N; j++)
            printf("%d ", ans[i][j]);
        printf("\n");
    }

    return 0;
}