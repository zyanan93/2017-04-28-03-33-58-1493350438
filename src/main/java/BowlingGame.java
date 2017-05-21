public class BowlingGame {

    public static int[] judge_last(String str){

        int [] output = new int[2];
        if (str.equals("XX")){
            output[0] = 10;
            output[1] = 10;
        }else if (str.equals("")){
            output[0] = 0;
            output[1] = 0;
        }else{
            int a = 0;
            int b = 0;
            if(str.length() == 1){
                a = Integer.parseInt(str);
                b = 0;
            }else{
                a = Integer.parseInt(str.split("")[0]);
                b = Integer.parseInt(str.split("")[1]);
            }
            output[0] = a;
            output[1] = b;
        }
        return output;
    }


    public static int getBowlingScore(String bowlingCode) {

        String input = bowlingCode.replace("||","|");

        int[] score_arr = new int[22];
        int[] flag_arr = new int[10];
        String [] split_1 = input.split("\\|"); //11
        String last_two = "";
        if (split_1.length == 10){
            last_two = "";
        }else{
            last_two = split_1[10];
        }

        int [] output = judge_last(last_two);
        score_arr[20] = output[0];
        score_arr[21] = output[1];

        for(int index = 9; index >=0; index--){
            if(split_1[index].equals("X")){
                score_arr[2*index] = 10;
                score_arr[2*index+1] = -1;
                flag_arr[index] = 0;
            }else if(split_1[index].endsWith("/")){
                String tmp = split_1[index].replace("/","");
                score_arr[2*index] = Integer.parseInt(tmp);
                score_arr[2*index + 1] = 10 - Integer.parseInt(tmp);
                flag_arr[index] = 1;
            }else if (split_1[index].contains("-")){
                String s = split_1[index];
                s = s.replace("-","0");
                String [] s_split = s.split("");
                score_arr[2*index] = Integer.parseInt(s_split[0]);
                score_arr[2*index + 1] = Integer.parseInt(s_split[1]);
                flag_arr[index] = 2;
            }else{
                String [] s_split = split_1[index].split("");
                score_arr[2*index] = Integer.parseInt(s_split[0]);
                score_arr[2*index + 1] = Integer.parseInt(s_split[1]);
                flag_arr[index] = 3;
            }
        }

        //for(int i = 0;i<22;i++){
        //    System.out.println(score_arr[i]);
        //}
        System.out.println();
        int sum = 0;
        for(int i = 0;i<9;i++){
            //System.out.println(i + ":");
            if(flag_arr[i] == 0){
                int next_1 = score_arr[2 * (i+1)];
                int next_2 = 0;
                if(next_1 == 10){
                    next_2 = score_arr[2 * (i+2)];
                } else{
                    next_2 = score_arr[2 * (i+1) + 1];
                }
                //System.out.println(10 + next_1 + next_2);
                sum += (10 + next_1 + next_2);

            }else if(flag_arr[i] == 1){
                //System.out.println(10 + score_arr[2 * (i+1)]);
                sum += (10 + score_arr[2 * (i+1)]);
            }else{
                //System.out.println(score_arr[2 * i] + score_arr[2 * i+1]);
                sum += (score_arr[2 * i] + score_arr[2 * i+1]);
            }

        }
        if(flag_arr[9] == 0){
            //System.out.println(10 + score_arr[20] + score_arr[21]);
            sum += (10 + score_arr[20] + score_arr[21]);

        }else if(flag_arr[9] == 1){
            //System.out.println(10 + score_arr[20]);
            sum += (10 + score_arr[20]);
        }else{
            //System.out.println(score_arr[18] + score_arr[19]);
            sum += (score_arr[18] + score_arr[19]);
        }


        return sum;
    }
}
