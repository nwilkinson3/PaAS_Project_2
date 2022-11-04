function bmiGraph()
  matrix = bmiMatrix();
  plot(matrix(:,1), matrix(:,3), 'k.', 'MarkerSize', 6, [48,84], [17.5,17.5], ':m', [48,84], [18.4,18.4], ':b', [48,84], [25.1,25.1], ':r', [48,84], [40.1,40.1], ':k');
  title("Height and BMI of Group");
  xlabel("Height");
  ylabel("BMI");
  set(gca, 'fontsize', 24);
  axis([48, 84, 12, 85]);
endfunction
