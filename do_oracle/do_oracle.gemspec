# Generated by jeweler
# DO NOT EDIT THIS FILE DIRECTLY
# Instead, edit Jeweler::Tasks in Rakefile, and run the gemspec command
# -*- encoding: utf-8 -*-

Gem::Specification.new do |s|
  s.name = %q{do_oracle}
  s.version = "0.10.1"

  s.required_rubygems_version = Gem::Requirement.new(">= 0") if s.respond_to? :required_rubygems_version=
  s.authors = ["Raimonds Simanovskis"]
  s.date = %q{2009-12-06}
  s.description = %q{Implements the DataObjects API for Oracle}
  s.email = %q{raimonds.simanovskis@gmail.com}
  s.extensions = ["ext/do_oracle_ext/extconf.rb"]
  s.extra_rdoc_files = [
    "README.markdown"
  ]
  s.files = [
    "HISTORY.markdown",
     "INSTALL.markdown",
     "LICENSE",
     "README.markdown",
     "Rakefile",
     "ext/do_oracle_ext/do_oracle_ext.c",
     "ext/do_oracle_ext/extconf.rb",
     "lib/do_oracle.rb",
     "lib/do_oracle/transaction.rb",
     "lib/do_oracle/version.rb",
     "spec/command_spec.rb",
     "spec/connection_spec.rb",
     "spec/encoding_spec.rb",
     "spec/lib/rspec_immediate_feedback_formatter.rb",
     "spec/reader_spec.rb",
     "spec/result_spec.rb",
     "spec/spec_helper.rb",
     "spec/typecast/array_spec.rb",
     "spec/typecast/bigdecimal_spec.rb",
     "spec/typecast/boolean_spec.rb",
     "spec/typecast/byte_array_spec.rb",
     "spec/typecast/class_spec.rb",
     "spec/typecast/date_spec.rb",
     "spec/typecast/datetime_spec.rb",
     "spec/typecast/float_spec.rb",
     "spec/typecast/integer_spec.rb",
     "spec/typecast/nil_spec.rb",
     "spec/typecast/range_spec.rb",
     "spec/typecast/string_spec.rb",
     "spec/typecast/time_spec.rb",
     "tasks/compile.rake",
     "tasks/retrieve.rake",
     "tasks/spec.rake"
  ]
  s.has_rdoc = false
  s.rdoc_options = ["--charset=UTF-8"]
  s.require_paths = ["lib"]
  s.rubyforge_project = %q{dorb}
  s.rubygems_version = %q{1.3.5}
  s.summary = %q{DataObjects Oracle Driver}

  if s.respond_to? :specification_version then
    current_version = Gem::Specification::CURRENT_SPECIFICATION_VERSION
    s.specification_version = 3

    if Gem::Version.new(Gem::RubyGemsVersion) >= Gem::Version.new('1.2.0') then
      s.add_runtime_dependency(%q<addressable>, ["~> 2.1"])
      s.add_runtime_dependency(%q<data_objects>, ["= 0.10.1"])
      s.add_runtime_dependency(%q<ruby-oci8>, ["~> 2.0"])
      s.add_development_dependency(%q<bacon>, ["~> 1.1"])
    else
      s.add_dependency(%q<addressable>, ["~> 2.1"])
      s.add_dependency(%q<data_objects>, ["= 0.10.1"])
      s.add_dependency(%q<ruby-oci8>, ["~> 2.0"])
      s.add_dependency(%q<bacon>, ["~> 1.1"])
    end
  else
    s.add_dependency(%q<addressable>, ["~> 2.1"])
    s.add_dependency(%q<data_objects>, ["= 0.10.1"])
    s.add_dependency(%q<ruby-oci8>, ["~> 2.0"])
    s.add_dependency(%q<bacon>, ["~> 1.1"])
  end
end

