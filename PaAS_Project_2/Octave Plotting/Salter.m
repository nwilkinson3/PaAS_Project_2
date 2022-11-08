function points = Salter()
  points = csvread(input("Enter name of file (put in quotes): "));
  salting = input("Enter salting value: ");
  [numR, numC] = size(points);
  for i = 1:1:numR
    if rand > 0.5
      points(i,2) = points(i,2) + salting * rand;
    else
      points(i,2) = points(i,2) - salting * rand;
    endif
  endfor
  plot(points(:,1), points(:,2));
  csvwrite("salt.csv", points);
endfunction
