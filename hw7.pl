% Olliver Aikenhead
% homework 7

% testing problem 1a -----------------------------------------------------------------

isSet([]).
isSet([H|T]):-
  \+member(H,T), isSet(T).

% testing problem 1b -----------------------------------------------------------------

isSubset([H|T],X):-
  member(H,X), isSubset(T,X).
isSubset([],_):-
  write('Yes').

% testing problem 1c -----------------------------------------------------------------

unionSets([H|T],X,Y):-
  member(H,X), unionSets(T,X,Y).
unionSets([H|T],X,[H|Y]):-
  \+ member(H,X), unionSets(T,X,Y).
unionSets([],X,X).

% testing problem 1d -----------------------------------------------------------------

intersectionSets([],_,[]).
intersectionSets([X|A],B,C):-
  not(member(X,B)), intersectionSets(A,B,C).
intersectionSets([X|A],B,[X|C]):-
  member(X,B), intersectionSets(A,B,C).


% testing problem 2 ------------------------------------------------------------------

tally(_,[],0).
tally(X,[X|T],N):-
  !, tally(X,T,N1), N is N1 + 1.
tally(X,[_|T],N):-
  tally(X,T,N).

% testing problem 3 ------------------------------------------------------------------

% empty list
subst(_,_,[],[]).
% first element in list
subst(X,Y,[X|T],[Y|NT]):-
  subst(X,Y,T,NT).
% calling recursively
subst(X,Y,[H|T],[H|NT]):-
  H \= X, subst(X,Y,T,NT).

% testing problem 4 ------------------------------------------------------------------

insert(X,[],[X]).
insert(X,[H|T0], [X,H|T0]):-
  X@<H,!.
insert(X,[H|T1],[H|T2]):-
  insert(X,T1,T2).

% testing problem 5 ------------------------------------------------------------------

flattenList([],[]):-!.
flattenList([H|T],FL):-
  !, flattenList(H,NL1), flattenList(T,NL2), append(NL1,NL2,FL).
flattenList(H,[H]).

% end of file ------------------------------------------------------------------------
