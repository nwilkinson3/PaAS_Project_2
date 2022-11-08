function points = Plotter ()
  first = input("Start of plot: ");
  last = input("End of plot: ");
  x = [first:.01:last];
  y = sin(pi * x);
  plot(x,y);
  points = [x ; y];
  points = points';
  csvwrite("plot.csv", points);
endfunction

