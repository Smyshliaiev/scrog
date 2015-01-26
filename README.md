# scrog
Android UI logger library.

Manual.

I.Add scrog library:

Put scrog-1.0.0.aar in the (f.e.) libs folder in your project.

1.1 For Gradle manually

Add to your gradle file where the repositories section is:

flatDir {dirs 'libs'}
          
    for example:
    
repositories {

    flatDir {
 
    dirs 'libs'
    
    }
    
}

1.2 Add to your gradle file where the dependencies section is:

compile(name:'scrog-1.0.0', ext:'aar')

for example:

dependencies {

    compile(name:'scrog-1.0.0', ext:'aar')
    
}


2.1 Or for Android Studio 

Ready .aar library you may find in the "aar" directory of this project. 
Or you may build the project.

File->New Module->Import .jar or .aar package.

2.2 File->Project Structure->Dependencies 

And add module dependency for scrog-1.0.0 module.

II. Usage:

1. Add f.e. to onCreate():

        Scrog.init(this);
		
2. Add f.e. to onDestroy();       

        Scrog.destroy();
		
3. For printing:

        Scrog.i("text");      
