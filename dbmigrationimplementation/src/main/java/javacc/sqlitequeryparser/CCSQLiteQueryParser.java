/* CCSQLiteQueryParser.java */
/* Generated By:JavaCC: Do not edit this line. CCSQLiteQueryParser.java */
package javacc.sqlitequeryparser;

import db.migration.model.*;
import db.migration.model.modification.*;
import db.migration.model.modification.create.*;
import java.util.ArrayList;
import java.util.List;


public class CCSQLiteQueryParser implements CCSQLiteQueryParserConstants {

    public DBChange analiseQuery() throws ParseException, TokenMgrError{
        return analise();
    }

  final public DBChange analise() throws ParseException {DBChange dbChange = null;
    dbChange = createTable();
{if ("" != null) return dbChange;}
    throw new Error("Missing return statement in function");
  }

  final public CreateTable createTable() throws ParseException {CreateTable createTable = new CreateTable();
    Table table = null;
    List<ColumnDefinition> columnDefinitions = new ArrayList<ColumnDefinition>();
    String columnName = null;
    String columnDataType = null;
    ColumnDefinition columnDef = null;
    List<String> columnSpecs = new ArrayList<String>();
    jj_consume_token(T_CREATE_TABLE);
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case T_IF:{
      jj_consume_token(T_IF);
      jj_consume_token(T_NOT);
      jj_consume_token(T_EXISTS);
createTable.setIfNotExists(true);
      break;
      }
    default:
      jj_la1[0] = jj_gen;
      ;
    }
    table = Table();
    jj_consume_token(T_OPEN_BRACKET);
    columnDefinitions = ColumnDefinitions();
    jj_consume_token(T_CLOSE_BRACKET);
    jj_consume_token(0);
createTable.setTable(table);
                createTable.setColumnDefinitions(columnDefinitions);
                {if ("" != null) return createTable;}
    throw new Error("Missing return statement in function");
  }

  final public Table Table() throws ParseException {Token schemaToken = new Token();
    Token tableToken = null;
    if (jj_2_1(3)) {
      schemaToken = jj_consume_token(T_IDENTIFIER);
      jj_consume_token(23);
      tableToken = jj_consume_token(T_IDENTIFIER);
    } else {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case T_IDENTIFIER:{
        tableToken = jj_consume_token(T_IDENTIFIER);
        break;
        }
      default:
        jj_la1[1] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
Table table = new Table(schemaToken.image,tableToken.image);
        {if ("" != null) return table;}
    throw new Error("Missing return statement in function");
  }

  final public List<ColumnDefinition> ColumnDefinitions() throws ParseException {List<ColumnDefinition> columnDefinitions = new ArrayList<ColumnDefinition>();
    ColumnDefinition columnDef = null;
    String columnName = null;
    String columnDataType = null;
    String spec = null;
    List<String> columnSpecs = new ArrayList<String>();
    Token columnToken = null;
    Token typeToken = null;
    columnToken = jj_consume_token(T_IDENTIFIER);
    typeToken = jj_consume_token(T_IDENTIFIER);
columnName = columnToken.image;
        columnDataType = typeToken.image;
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case T_PRIMARY_KEY:
      case T_NOT:
      case T_UNIQUE:
      case T_DEFAULT:{
        ;
        break;
        }
      default:
        jj_la1[2] = jj_gen;
        break label_1;
      }
      spec = ColumnSpec();
columnSpecs.add(spec);
    }
columnDef = new ColumnDefinition(columnName,columnDataType);
        columnDef.setColumnSpecs(new ArrayList<String>(columnSpecs));
                columnSpecs.clear();
        columnDefinitions.add(columnDef);
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case T_COMMA:{
        ;
        break;
        }
      default:
        jj_la1[3] = jj_gen;
        break label_2;
      }
      jj_consume_token(T_COMMA);
      columnToken = jj_consume_token(T_IDENTIFIER);
      typeToken = jj_consume_token(T_IDENTIFIER);
columnName = columnToken.image;
            columnDataType = typeToken.image;
      label_3:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case T_PRIMARY_KEY:
        case T_NOT:
        case T_UNIQUE:
        case T_DEFAULT:{
          ;
          break;
          }
        default:
          jj_la1[4] = jj_gen;
          break label_3;
        }
        spec = ColumnSpec();
columnSpecs.add(spec);
      }
columnDef = new ColumnDefinition(columnName,columnDataType);
            columnDef.setColumnSpecs(new ArrayList<String>(columnSpecs));
                        columnSpecs.clear();
            columnDefinitions.add(columnDef);
    }
{if ("" != null) return columnDefinitions;}
    throw new Error("Missing return statement in function");
  }

  final public String ColumnSpec() throws ParseException {Token token = null;
    Token token1 = null;
    String spec = null;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case T_PRIMARY_KEY:{
      token = jj_consume_token(T_PRIMARY_KEY);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case T_AUTOINCREMENT:{
        token1 = jj_consume_token(T_AUTOINCREMENT);
        break;
        }
      default:
        jj_la1[5] = jj_gen;
        ;
      }
spec = token.image;
                if(token1!=null){
                        spec+=" "+token1.image;
                }
                {if ("" != null) return spec;}
      break;
      }
    default:
      jj_la1[6] = jj_gen;
      if (jj_2_2(2)) {
        token = jj_consume_token(T_NOT);
        token1 = jj_consume_token(T_NULL);
{if ("" != null) return token.image + " " + token1.image;}
      } else {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case T_UNIQUE:{
          token = jj_consume_token(T_UNIQUE);
{if ("" != null) return token.image;}
          break;
          }
        default:
          jj_la1[7] = jj_gen;
          if (jj_2_3(2)) {
            token = jj_consume_token(T_DEFAULT);
            jj_consume_token(T_APOSTR);
            token1 = jj_consume_token(T_IDENTIFIER);
            jj_consume_token(T_APOSTR);
{if ("" != null) return token.image +" "+"'"+token1.image+"'";}
          } else {
            jj_consume_token(-1);
            throw new ParseException();
          }
        }
      }
    }
    throw new Error("Missing return statement in function");
  }

  private boolean jj_2_1(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  private boolean jj_2_2(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  private boolean jj_2_3(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_3(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(2, xla); }
  }

  private boolean jj_3_3()
 {
    if (jj_scan_token(T_DEFAULT)) return true;
    if (jj_scan_token(T_APOSTR)) return true;
    return false;
  }

  private boolean jj_3_2()
 {
    if (jj_scan_token(T_NOT)) return true;
    if (jj_scan_token(T_NULL)) return true;
    return false;
  }

  private boolean jj_3_1()
 {
    if (jj_scan_token(T_IDENTIFIER)) return true;
    if (jj_scan_token(23)) return true;
    if (jj_scan_token(T_IDENTIFIER)) return true;
    return false;
  }

  /** Generated Token Manager. */
  public CCSQLiteQueryParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private Token jj_scanpos, jj_lastpos;
  private int jj_la;
  private int jj_gen;
  final private int[] jj_la1 = new int[8];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x8000,0x100000,0x62800,0x400,0x62800,0x1000,0x800,0x20000,};
   }
  final private JJCalls[] jj_2_rtns = new JJCalls[3];
  private boolean jj_rescan = false;
  private int jj_gc = 0;

  /** Constructor with InputStream. */
  public CCSQLiteQueryParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public CCSQLiteQueryParser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new CCSQLiteQueryParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public CCSQLiteQueryParser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new CCSQLiteQueryParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public CCSQLiteQueryParser(CCSQLiteQueryParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(CCSQLiteQueryParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  @SuppressWarnings("serial")
  static private final class LookaheadSuccess extends java.lang.Error { }
  final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk_f() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;
  private int[] jj_lasttokens = new int[100];
  private int jj_endpos;

  private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      jj_entries_loop: for (java.util.Iterator<?> it = jj_expentries.iterator(); it.hasNext();) {
        int[] oldentry = (int[])(it.next());
        if (oldentry.length == jj_expentry.length) {
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              continue jj_entries_loop;
            }
          }
          jj_expentries.add(jj_expentry);
          break jj_entries_loop;
        }
      }
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[24];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 8; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 24; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

  private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 3; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
            case 1: jj_3_2(); break;
            case 2: jj_3_3(); break;
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

}
