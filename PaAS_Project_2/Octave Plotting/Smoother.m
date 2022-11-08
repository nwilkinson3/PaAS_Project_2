function points = Smoother ()

  salted = csvread(input("Enter name of file (put in quotes): "));
  [smoothedy, lambda] = regdatasmooth (salted(:, 1), salted(:, 2));
  points = [salted(:, 1) ; smoothedy];
  points = points';
  plot(points(:,1), points(:,2));
  csvwrite("smooth.csv", points);
endfunction
