# scrog
Android UI logger library.

Manual.

I.Add scrog library:

1. For Android Studio 

File->New Module->Import .jar or .aar package.

2. File->Project Structure->Dependencies 

And add module dependency for scrog-1.0.0 module.

II. Usage:

1. Add f.e. to onCreate():

        Scrog.init(this);
		
2. Add f.e. to onDestroy();       

        Scrog.destroy();
		
3. For printing:

        Scrog.i("text");        
