function points = Plotter ()
  first = input("Start index of plot: ",);
  last = input("End index of plot: ");
  x = [first:.01:last];
  y = sin(pi * x);
  points = [x ; y];
  points = points';
  csvwrite(strcat(input("Enter name of output file (assumes .csv): ", "s"), ".csv"), points);
  plot(points(:,1), points(:,2));
endfunction

