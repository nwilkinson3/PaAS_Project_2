function points = Smoother ()
  points = csvread(strcat(input("Enter name of csv file to smooth (assumes .csv): ", "s"), ".csv"));
  scanRange = input("Enter the scan range for smoothing: ");
  [numR, numC] = size(points);
  right = 0;
  left = 0;
  if rem(numR, 2) == 1
    mid = round(numR / 2);
    while numR-mid < scanRange
      scanRange--;
    endwhile
    smoothdata = [];
    for j = mid-scanRange:1:mid+scanRange
      if j != mid
        smoothdata = [smoothdata, points(j, 2)];
      endif
    endfor
    points(mid, 2) = mean(smoothdata);
    right = mid + 1;
    left = mid - 1;
  else
    right = numR / 2;
    left = right - 1;
  endif
  while right < numR
    while numR-right < scanRange
      scanRange--;
    endwhile
    smoothdata = [];
    for j = left-scanRange:1:left+scanRange
      if j != left
        smoothdata = [smoothdata, points(j, 2)];
      endif
    endfor
    points(left, 2) = mean(smoothdata);
    smoothdata = [];
    for j = right-scanRange:1:right+scanRange
      if j != right
        smoothdata = [smoothdata, points(j, 2)];
      endif
    endfor
    points(right, 2) = mean(smoothdata);
    right++;
    left--;
  endwhile
  csvwrite(strcat(input("Enter name of output file (assumes .csv): ", "s"), ".csv"), points);
  plot(points(:, 1), points(:, 2));
endfunction
