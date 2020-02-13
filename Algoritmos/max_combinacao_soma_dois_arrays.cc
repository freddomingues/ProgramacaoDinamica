#include <bits/stdc++.h>
using namespace std;


// Função para combinação máxima com dois arrays
int Max_Sum(int arr1[], int arr2[], int n)
{
    //Armazenar valor
    int dp[n][2];

    // For para calcular e armazenar valor
    for (int i = 0; i < n; i++)
    {
        if(i==0)
        {
            dp[i][0] = arr1[i];
            dp[i][1] = arr2[i];
            continue;
        }

       dp[i][0] = max(dp[i - 1][0], dp[i - 1][1] + arr1[i]);
       dp[i][1] = max(dp[i - 1][1], dp[i - 1][0] + arr2[i]);
     }

    // Return the required answer
    return max(dp[n-1][0], dp[n-1][1]);
}

// Driver code
int main()
{
    int arr1[] = {9, 3, 5, 7, 3};
    int arr2[] = {5, 8, 1, 4, 5};

    int n = sizeof(arr1) / sizeof(arr1[0]);

    // Function call
    cout << Max_Sum(arr1, arr2, n);

    return 0;
}
