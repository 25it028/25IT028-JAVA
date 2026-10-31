#include <iostream>
using namespace std;
struct Node{
    int data;
    Node* next;
};
insertBeginning(Node*&head, int value){
    Node*newnode = new Node;
    newnode->data=value;
    newnode->next=head;
    head=newnode;

}
int main(){
    Node* head=NULL;
    insertBeginning(head, 5);
    return 0;
}