function points = Salter()
  points = csvread(strcat(input("Enter name of csv file to salt (assumes .csv): ", "s"), ".csv"));
  salting = input("Enter salting value: ");
  [numR, numC] = size(points);
  for i = 1:1:numR
    if rand > 0.5
      points(i,2) = points(i,2) + salting * rand;
    else
      points(i,2) = points(i,2) - salting * rand;
    endif
  endfor
  csvwrite(strcat(input("Enter name of output file (assumes .csv): ", "s"), ".csv"), points);
  plot(points(:,1), points(:,2));
endfunction
