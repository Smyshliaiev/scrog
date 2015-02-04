# scrog
Android UI logger library.

## Main features: 

1. Always on the screen, even if app is paused. It can be destroyed after direct command call.
2. You can drag it to the any place of the screen.
3. Font and background colors can be changed by double tap.
4. You can resize main log window by dragging down right corener of the log window.
5. Clear the screen by clicking on the right upper corner.
6. Easy use. Only three API methods.
7. Easy link with Gradle.

<TABLE cellpadding="100" cellspacing="20" border="0">
<TR>
<TD>
<a href="http://tinypic.com?ref=33w3w9g" target="_blank"><img src="http://i59.tinypic.com/33w3w9g.png" border="0" alt="Image and video hosting by TinyPic"></a>
</TD>
<TD>
<a href="http://tinypic.com?ref=qou1hi" target="_blank"><img src="http://i59.tinypic.com/qou1hi.png" border="0" alt="Image and video hosting by TinyPic"></a>
</TD>
</TR>
</TABLE>

# Manual.

## I.Add scrog library:

Put scrog-1.0.0.aar in the (f.e.) libs folder in your project.

## 1.1 For Gradle manually

Add to your gradle file where the repositories section is:
```
flatDir {dirs 'libs'}
```          
for example:
```    
repositories {

    flatDir {
 
    dirs 'libs'
    
    }
    
}
```
## 1.2 Add to your gradle file where the dependencies section is:
```
compile(name:'scrog-1.0.0', ext:'aar')
```
for example:
```
dependencies {

    compile(name:'scrog-1.0.0', ext:'aar')
    
}
```

## 2 Or for Android Studio 

Ready .aar library you may find in the "aar" directory of this project. 
Or you may build the project.

File->New Module->Import .jar or .aar package.

File->Project Structure->Dependencies 

And add module dependency for scrog-1.0.0 module.

# II. Usage:

## 1. Add f.e. to onCreate():
```
        Scrog.init(this);
```		
## 2. Add f.e. to onDestroy();       
```
        Scrog.destroy();
```		
## 3. For printing:
```
        Scrog.i("text");      
```
