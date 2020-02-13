#include <iostream>
using namespace std;

// Recursive function to count number of paths
int countPaths(int n, int m)
{
    // If we reach bottom or top left, we are
    // have only one way to reach (0, 0)
    if (n==0 || m==0)
        return 1;

    // Else count sum of both ways
    return (countPaths(n-1, m) + countPaths(n, m-1));
}
