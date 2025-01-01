// eratosthenes : sang so nguyen to
#include <bits/stdc++.h>
using namespace std;
vector<int> sang(int n){
    vector<int> P;
    bool a[n];
    fill(a,a+n+1,1);// a0 a1.......an=true
    for(int i=2;i<=n;i++)
    if(a[i])
    {
        P.push_back(i);
        for(int j=i*i;j<=n;j+=i)
            a[j]=false;
    }
    return P;
}
int main(){
    int n;
    cout<<"nhap n= ";cin>>n;
    vector<int> value= sang(n);
    for(auto c: value) cout<<c<<" ";
}