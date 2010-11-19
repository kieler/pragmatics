// test

#include <iostream>

#include <ogdf/basic/exceptions.h>

using namespace ogdf;

int main(int argc, char* argv[]) {

	Exception e = new Exception("keks");
	std::cout << e.file << std::endl;
	
	return 0;
}