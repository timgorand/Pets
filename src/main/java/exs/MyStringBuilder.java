package exs;

import java.util.Stack;

/*Напишите свой класс StringBuilder с поддержкой операции undo. Для этого делегируйте все методы стандартному StringBuilder,
а в собственном классе храните список всех операций для выполнения undo().
*/
class StringBuilder_undo {
    private interface Action {
        void undo();
    }
    class DeleteAction implements Action {
        private int size;

        public DeleteAction(int size) {
            this.size = size;
        }

        public void undo() {
            stringBuilder.delete(
                    stringBuilder.length() - size, stringBuilder.length());
        }
    }
    private StringBuilder stringBuilder;
    private Stack<Action> act = new Stack<>();
    public StringBuilder_undo(){
        stringBuilder = new StringBuilder();
    }
    public StringBuilder_undo reverse(){
        stringBuilder.reverse();

        Action action = new Action(){
            public void undo(){
                stringBuilder.reverse();
            }
        };
        act.add(action);
        return this;
    }

    public StringBuilder_undo append (String str){
        stringBuilder.append(str);
        Action action = new Action() {
            public void undo() {
                stringBuilder.delete(
                        stringBuilder.length() - str.length() -1,
                        stringBuilder.length());
            }
        };
        act.add(action);
        return this;
        }
    public StringBuilder_undo appendCodePoint(int codePoint) {
        int lenghtBefore = stringBuilder.length();
        stringBuilder.appendCodePoint(codePoint);
        act.add(new StringBuilder_undo.DeleteAction(stringBuilder.length() - lenghtBefore));
        return this;
    }

    public StringBuilder_undo delete(int start, int end) {
        String deleted = stringBuilder.substring(start, end);
        stringBuilder.delete(start, end);
        act.add(() -> stringBuilder.insert(start, deleted));
        return this;
    }

    public StringBuilder_undo deleteCharAt(int index) {
        char deleted = stringBuilder.charAt(index);
        stringBuilder.deleteCharAt(index);
        act.add(() -> stringBuilder.insert(index, deleted));
        return this;
    }

    public StringBuilder_undo replace(int start, int end, String str) {
        String deleted = stringBuilder.substring(start, end);
        stringBuilder.replace(start, end, str);
        act.add(() -> stringBuilder.replace(start, end, deleted));
        return this;
    }

    public StringBuilder_undo insert(int index, char[] str, int offset, int len) {
        stringBuilder.insert(index, str, offset, len);
        act.add(() -> stringBuilder.delete(index, len));
        return this;
    }

    public StringBuilder_undo insert(int offset, String str) {
        stringBuilder.insert(offset, str);
        act.add(() -> stringBuilder.delete(offset, str.length()));
        return this;
    }

    public void undo(){
        if(!act.isEmpty()){
            act.pop().undo();
        }
    }
}
