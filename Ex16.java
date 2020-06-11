package Jeff.exercise.c13;

import util.print;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class Ex16 {
    static int tiefe=0;
    static Map<String,Integer> struktur= new LinkedHashMap<String, Integer>();
    static File Ergebnisfile = new File("/Users/qianjiannan/Desktop/ErgebnisOrdner.txt");
    static boolean create=false;
    static BufferedWriter buf;

    static Map<String,Integer> getfile(File path)
    {
        File[] list = path.listFiles();



        for(File f: list)
        {
            if(f.isFile())
            struktur.put(f.getName(),tiefe);
            else if(f.isDirectory()) {
                tiefe++;
                getfile(new File(f.getPath()));

            }
        }
        return struktur;
    }
    public static void main(String[] args) {
        File file;
        //File Ergebnisfile = new File(args[1]);

        try {

                Ergebnisfile.createNewFile();
                buf = new BufferedWriter(new FileWriter(Ergebnisfile));

        }catch (Exception e)
        {
            print.print("File cannot Created");
            System.exit(-1);
        }





        List<String> Inhalt= new LinkedList<String>();
       String path= args[0];
       Ex16 ex16= new Ex16();
        file=new File(path);
        if(file.isFile())
        {
            Inhalt.add(file.getName());

        }
        else if(file.isDirectory())
        {
            //print.print(Ex16.getfile(file));
            printordner(getfile(file));
        }
       }
       static void printordner(Map<String,Integer> struktur)
       {

           Set<String> name=struktur.keySet();
           LinkedHashMap<String,Integer> Erg= new LinkedHashMap<String,Integer>();
           LinkedList<String> ordnen= new LinkedList<String>();
           int offset=0;
           String Dateiname=null;
           boolean erst=true;
           for(String s:name)
           {
              if(s!=".DS_Store") {
                  offset = struktur.get(s);
                  Dateiname = s;

                  for (int i = 0; i < offset; i++) {
                      s = "---" + s;
                  }
                  Erg.put(s,offset);
              }

           }
           name.clear();
          List <Map.Entry<String,Integer>> test =new ArrayList<>(Erg.entrySet());
           //Print Ergebnisse Reihenfolge optimieren
            test.sort(new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    return o1.getValue().compareTo(o2.getValue());
                }
            });
            for (Map.Entry get:test)
            {
               // print.print(get.getKey());
                Write2File(((String)get.getKey())+"\n");

            }
           try {
               buf.flush();
           }catch (Exception e)
           {
               print.print("Error");
           }

       }
      static void Write2File(String s)
       {

           try {
               buf.write(s);
           }catch (Exception e)
           {
               print.print("Cannot be write");
               System.exit(1);
           }
       }

    }


