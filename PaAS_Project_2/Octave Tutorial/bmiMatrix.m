function matrix = bmiMatrix()
  hwm = heightWeightMatrix();
  [numRow, numCol] = size(hwm);
  matrix = zeros(numRow, 4);
  i = 1;
  for i = 1:1:numRow
    matrix(i,1) = hwm(i,1);
    matrix(i,2) = hwm(i,2);
    bmi = (703 * hwm(i,2)) / (hwm(i,1)^2);
    matrix(i,3) = bmi;
    if bmi <= 17.5
      matrix(i,4) = -2; % Severly Underweight
    elseif bmi <= 18.4
      matrix(i,4) = -1; % Underweight
    elseif bmi <= 25
      matrix(i,4) = 0; % Healthy
    elseif bmi <= 40
      matrix(i,4) = 1;  % Overweight
    else % bmi >= 40.1
      matrix(i,4) = 2;  % Obese
    endif
    ++i;
  endfor
endfunction
