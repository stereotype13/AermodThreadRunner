#include <iostream>

using namespace std;

//Just a test app to simulate shutting down an Amazon Web Services (AWS) instance.

int main(int argc, char const *argv[])
{
	cout << "STOPPINGINSTANCES\ti-b87b3c4f" << endl;
	cout << "CURRENTSTATE\t64\tstopping" << endl;
	cout << "PREVIOUSSTATE\t16\trunning" << endl;
	cout << "ubuntu@ip-172-31-25-10:~$" << endl;
	cout << "Boradcast message from root@ip-172-31-25-10\n(unknown) at 21:33 ..." << endl;
	cout << "\nThe system is going down for power off NOW!" << endl;
	return 0;
}