function matrix = heightWeightMatrix()
  num = input('How many individuals?: ');
  matrix = zeros(num,2);
  for i = 1:1:num
    fprintf("Individual %d\n", i);
    matrix(i,1) = input("What is the individual's height (in inches)?: ");
    matrix(i,2) = input("What is the individual's weight (in pounds)?: ");
  endfor
endfunction
